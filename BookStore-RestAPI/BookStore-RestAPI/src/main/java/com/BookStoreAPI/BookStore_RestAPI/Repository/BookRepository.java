package com.BookStoreAPI.BookStore_RestAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookStoreAPI.BookStore_RestAPI.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
