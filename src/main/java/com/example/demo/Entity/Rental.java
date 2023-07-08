package com.example.demo.Entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Rental {
	
	private int rent_id;
	
	private int user_id;
	
	private int book_id;
	
	private Date rent_day;
	
	private Date return_day;
	
	private boolean dlt_flg;
	
	private String book_name;

}
