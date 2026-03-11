package com.ShivnexEngineering.journalApp.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShivnexEngineering.journalApp.entity.JournalEntry;
import com.ShivnexEngineering.journalApp.entity.User;
import com.ShivnexEngineering.journalApp.service.JournalEntryService;
import com.ShivnexEngineering.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userName}")
	public ResponseEntity<?> getAllJournalEntry(@PathVariable String userName) {
		
		User existingUser = userService.findUserByUserName(userName);
		
		List<JournalEntry> allEntry = existingUser.getJournalEntries();
		
		if(allEntry!=null && !allEntry.isEmpty()) {
			return new ResponseEntity<>(allEntry, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/{userName}")
	public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry entry, @PathVariable String userName) {
		
		try {
			journalEntryService.saveEntry(entry, userName);
			return new ResponseEntity<JournalEntry>(entry, HttpStatus.CREATED);	
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		}
		
	}
	
	
	@GetMapping("/id/{userName}/{myId}")
	public ResponseEntity<JournalEntry> getSpecificJournalEntry(@PathVariable ObjectId myId, @PathVariable String userName) {
		
		Optional<JournalEntry> oldEntry = journalEntryService.getEntryById(myId, userName);
		
		if(oldEntry.isPresent()) {
			return new ResponseEntity<>(oldEntry.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/id/{userName}/{myId}")
	public ResponseEntity<?> deleteSpecificEntry(@PathVariable ObjectId myId, @PathVariable String userName) {
		
		journalEntryService.deleteEntryById(myId, userName);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
	}
	
	@PutMapping("/id/{userName}/{myId}")
	public ResponseEntity<?> updateSpecificEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry entry, 
			@PathVariable String userName) {
		
		journalEntryService.updateEntryById(entry, myId, userName);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
