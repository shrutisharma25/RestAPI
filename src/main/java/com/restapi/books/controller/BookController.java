package com.restapi.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.books.entities.Book;
import com.restapi.books.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//@RequestMapping(value="/books", method=RequestMethod.GET)
	//@ResponseBody
	@GetMapping("/books")
	public List<Book> getBooks() {
		return this.bookService.getAllBooks();
		// spring boot will automatically convert the data into json by json configuration
	}
	
	@GetMapping("/books/{id}")
	public Book getBook(@PathVariable("id") int id) {
		return bookService.getBookById(id);
	}
	
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		//@RequestBody will catch the json data and store it into Book object(book)
		Book b=this.bookService.addBook(book);
		return b;
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable("id") int id) {
		//@RequestBody will catch the json data and store it into Book object(book)
		this.bookService.deleteBook(id);
	}
	
	
	//@RequestBody :- Will convert the comming Json format data into the given variable(i.e Book) 
	@PutMapping("/books/{bookId}")
	public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		this.bookService.updateBook(book,bookId);
		return book;		
	}
	
}
