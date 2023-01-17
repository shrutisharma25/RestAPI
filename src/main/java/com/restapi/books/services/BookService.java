package com.restapi.books.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.restapi.books.entities.Book;
import com.restapi.books.repository.BookRepository;

@Component
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	//GET ALL BOOKS
	public List<Book> getAllBooks(){
		List<Book> list = (List<Book>)this.bookRepository.findAll();
		return list;
	}

	//GET BOOK DETAILS OF PARTICULAR ID
	public Book getBookById(int id){
		Book book =null;
		try {
			//Stream API function & Lambda function
			//book = list.stream().filter(e -> e.getId()==id ).findFirst().get();
			this.bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	//FOR ADDING BOOK DETAILS
	public Book addBook(Book b) {
		Book book = bookRepository.save(b);
		return book;
	}
	
	//FOR DELEING BOOK DETAILS
	public void deleteBook(int id) {
		//list=list.stream().filter( e -> e.getId()!=id ).collect(Collectors.toList());
		bookRepository.deleteById(id);
	}
	
	//FOR UPDATING THE BOOK DETAIL
	public void updateBook(Book book, int bookId) {
//		list=list.stream().map( b->{            //map():- will return all objects one by one and recieves an objects
//			if(b.getId() == bookId) 
//			{
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bookId);
		bookRepository.save(book);
	}
}
