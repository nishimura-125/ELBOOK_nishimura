package com.example.demo.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Entity.BookList;

@Mapper
public interface BookMapper {
	

	void createBook(BookList bookList);

	List<BookList> findBook();
	
	List<BookList> getBookList(int rental_key_id);
	
	int getMaxNumberBook();
	
	BookList targetBook();

	void editBook(BookList bookList);

	void deleteBook(int book_id);

}
