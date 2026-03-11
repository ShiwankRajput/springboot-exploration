package com.ShivnexEngineering.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ShivnexEngineering.journalApp.entity.JournalEntry;
import com.ShivnexEngineering.journalApp.entity.User;
import com.ShivnexEngineering.journalApp.repository.JournalEntryRepository;

@Service
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserService userService;
	
	public List<JournalEntry> findAllEntry(){
		return journalEntryRepository.findAll();
	}
	
	@Transactional
	public void saveEntry(JournalEntry journalEntry, String userName) {
		
		User existingUser = userService.findUserByUserName(userName);
		journalEntry.setDate(LocalDateTime.now());
		JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
		existingUser.getJournalEntries().add(savedEntry);
		userService.saveUser(existingUser); 
		
	}
	
	public Optional<JournalEntry> getEntryById(ObjectId id, String userName) {
		
		User existingUser = userService.findUserByUserName(userName);
		
		List<JournalEntry> journalEntries = existingUser.getJournalEntries();
		
		boolean isEntryExists = journalEntries.stream()
			.anyMatch(x -> x.getId().equals(id));
		
		if(isEntryExists) {
			return journalEntryRepository.findById(id);
		}
		
		return Optional.empty();
	}
	
	public void deleteEntryById(ObjectId id, String userName) {
		
		User existingUser = userService.findUserByUserName(userName);
		boolean isRemoved = existingUser.getJournalEntries().removeIf(x -> x.getId().equals(id));
		
		if(isRemoved) {
			userService.saveUser(existingUser);
			journalEntryRepository.deleteById(id);
		}
		
	}
	
	public void updateEntryById(JournalEntry newEntry, ObjectId id, String userName) {
		
		User existingUser = userService.findUserByUserName(userName);
		
		List<JournalEntry> journalEntries = existingUser.getJournalEntries();
		
		boolean isEntryExists = journalEntries.stream()
			.anyMatch(x -> x.getId().equals(id));
		
		if(isEntryExists) {
			
			journalEntryRepository.findById(id).ifPresent(entry ->{
				entry.setTitle(newEntry.getTitle());
				entry.setContent(newEntry.getContent());
				journalEntryRepository.save(entry);
			});
			
		}
		
	}
	
}
