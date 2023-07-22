package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.BookService;
import com.example.demo.Service.RentalService;
import com.example.demo.Service.auth.CustomDetails;

@Controller
@RequestMapping("/rental")
public class RentalController {

	@Autowired
	RentalService rentalService;
	@Autowired
	BookService bookService;
	
@GetMapping("/rentalview")
public String moveRentalView(
		@AuthenticationPrincipal CustomDetails user_info,
		Model model) {

	int rental_key_id = user_info.getUserList().getUser_id();
	model.addAttribute("RentalBooks", rentalService.findRentalBook(rental_key_id));
	return "rental";
}

@GetMapping("/list")
public String showRentalList(Model model) {
	model.addAttribute("RentalBooks", rentalService.findALLRentalBook());
	return "rentallist";
}

@PostMapping("/{book_id}")
public String rentalBook(
		@AuthenticationPrincipal CustomDetails user_info,
		@PathVariable int book_id) {

	int rental_key_id = user_info.getUserList().getUser_id();

	rentalService.rentalBook(rental_key_id, book_id);
	bookService.rentalBook(book_id);
	return "redirect:/book/list";
}

@GetMapping("/return/{book_id}")
public String returnBook(

		@AuthenticationPrincipal CustomDetails user_info,
		@PathVariable int book_id) {

	int rental_key_id = user_info.getUserList().getUser_id();

	rentalService.returnBook(rental_key_id, book_id);
	bookService.returnBook(book_id);
	return "redirect:/rental/rentalview";
}


}
