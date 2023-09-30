package com.example.demo.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Entity.UserList;

@Mapper
public interface UserMapper {



	void editUser(UserList userList);

	List<UserList> findUser();

	void createUser(UserList userList);
	
	void deleteUser(int user_id);

	UserList targetUser(int user_id);

	UserList emailFindUser(String maill);
	
	void emailDeleteUesr(String maill);
	
	
}