package com.restapi.books.repository;

import org.springframework.data.repository.CrudRepository;

import com.restapi.books.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer>{
	
	public Book findById(int id); 

}
