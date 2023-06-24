package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Entity.BookList;

@Mapper
public interface BookMapper {
	
//
	void createBook(BookList bookList);

//
	int getMaxNumberBook();
//

}
