package com.BookStoreAPI.BookStore_RestAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.BookStoreAPI.BookStore_RestAPI.Repository.BookRepository;
import com.BookStoreAPI.BookStore_RestAPI.entity.Book;

@Service
public class BookService {
	
	private BookRepository repository;
	
	public BookService(BookRepository repository) {
		this.repository = repository;
	}

	public List<Book> getAllSpecifiedBooks(){
		return repository.findAll();
	}
	
	public void EnterNewBook(Book book) {
		repository.save(book);
	}
	
	public Optional<Book> getSpecificBook(long id) {
		Optional<Book> existingBook = repository.findById(id);
		return existingBook;
	}
	
	public void deleteSpecificBook(long id) {
		repository.deleteById(id);
	}
	
}
