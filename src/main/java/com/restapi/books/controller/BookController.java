package com.restapi.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> list = this.bookService.getAllBooks();
		// spring boot will automatically convert the data into json by json configuration
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			/*      status():- static method in ResponseEntity class:- which receives http status
			 *                 as an input parameter
			 *      build():- For creating the object of the Message(Not Found)           
			 */       
		}
		return ResponseEntity.of(Optional.of(list));
		// of():- for creating the object of parameter passed by using Optional
		//	   Use :-	It will take the list and go with OK status code
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		if(book==null) { 
			//this will work only if the getBookById(id) will surrounds its component by try and catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		//@RequestBody will catch the json data and store it into Book object(book)
		Book b=null;
		try {
			b=this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
		//Void :- The Void class is an un-instantiable placeholder class to hold a reference 
		//        to the Class object representing the Java keyword void
		//@RequestBody will catch the json data and store it into Book object(book)
		try {
		    this.bookService.deleteBook(id);
			//return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); :- For sending message According to the Programmer
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	//@RequestBody :- Will convert the coming Json format data into the given variable(i.e Book) 
	@PutMapping("/books/{bookId}")
	public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		this.bookService.updateBook(book,bookId);
		return book;		
	}

}
