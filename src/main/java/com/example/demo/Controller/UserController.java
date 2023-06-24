package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.UserList;
import com.example.demo.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

//
	

	@GetMapping("/list")
	public String findUser(Model model) {
		model.addAttribute("UserList", userService.findUser());
		return "userlist";
	}
	
	@GetMapping("/editview/{user_id}")
	public String showEditUser(@PathVariable("user_id") int user_id ,Model model) {
		model.addAttribute("TargetUser", userService.targetUser(user_id));
		return "useredit";
	}
	
	@PostMapping("/useredit")
	public String editUser(@ModelAttribute("editUser") UserList userList) {
		System.out.println("edit" + userList.getPass());
		userService.editUser(userList);
		return "list";
	}
	
	@GetMapping("/createview")
	public String showUsercreatview(Model model) {
		return "usercreate";
	}
	
	@PostMapping("/usercreate")
	public String createUser(@ModelAttribute("createUser") UserList userList , Model model) {
		
		userService.createUser(userList);
		
		return "redirect:/user/list";
		
	}
	
	@GetMapping("/delete/{user_id}")
	public String deleteUser(@PathVariable("user_id") int user_id) {
		userService.deleteUser(user_id);
		return "redirect:/user/list";
	}
	

}
