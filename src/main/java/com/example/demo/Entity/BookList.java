package com.example.demo.Entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BookList {


	//ファイルアップロードのため必要
	private MultipartFile file;

	private String file_name;

	private int stock;

}
