package com.restapi.books.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.restapi.books.entities.Book;

@Component
public class BookService {

	private static List<Book> list=new ArrayList<Book>();

	static {
		list.add(new Book(12,"Phyton","YDG"));
		list.add(new Book(15,"JavaScript","KFJ"));
		list.add(new Book(17,"React JS","UGD"));
	}

	//GET ALL BOOKS
	public List<Book> getAllBooks(){

		return list;

	}

	//GET BOOK DETAILS OF PARTICULAR ID
	public Book getBookById(int id){
		Book book =null;
		try {
			//Stream API function & Lambda function
			book = list.stream().filter(e -> e.getId()==id ).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	//FOR ADDING BOOK DETAILS
	public Book addBook(Book b) {
		list.add(b);
		return b;
	}
	
	//FOR DELEING BOOK DETAILS
	public void deleteBook(int id) {
		list=list.stream().filter( e -> e.getId()!=id ).collect(Collectors.toList());
	}
	
	//FOR UPDATING THE BOOK DETAIL
	public void updateBook(Book book, int bookId) {
		list=list.stream().map( b->{            //map():- will return all objects one by one and recieves an objects
			if(b.getId() == bookId) 
			{
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());
	}
}
