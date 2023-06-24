package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserList;
import com.example.demo.Mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;


	public List<UserList> findUser() {
	return userMapper.findUser();
}


	public void createUser(UserList userList) {
		userMapper.createUser(userList);
	}
	
	public void editUser(UserList userList) {
		String encodedpass = passwordEncoder.encode(userList.getPass());
		userList.setPass(encodedpass);
		userMapper.editUser(userList);
	}
	
	public void deleteUser(int user_id) {
		userMapper.deleteUser(user_id);
	}


	public UserList targetUser(int user_id) {
		return userMapper.targetUser(user_id);
	}



}