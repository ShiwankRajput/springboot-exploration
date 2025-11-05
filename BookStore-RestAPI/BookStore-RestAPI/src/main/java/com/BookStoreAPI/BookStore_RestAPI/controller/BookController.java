package com.BookStoreAPI.BookStore_RestAPI.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.BookStoreAPI.BookStore_RestAPI.entity.Book;
import com.BookStoreAPI.BookStore_RestAPI.service.BookService;

@RestController
@RequestMapping("/api/bookStore")
public class BookController {

	private BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return service.getAllSpecifiedBooks();
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> createNewBook(@RequestBody Book book) {
		service.EnterNewBook(book);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(book.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable long id) {
		return service.getSpecificBook(id).get();
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBookById(@PathVariable long id) {
		service.deleteSpecificBook(id);
	}
	
}
