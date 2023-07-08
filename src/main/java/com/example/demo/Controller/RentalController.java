package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rental")
public class RentalController {

//	@Autowired
//	RentalService rentalService;
//	@Autowired
//	BookService bookService;
//	
//@GetMapping("/rentalview")
//public String moveRentalView(
//		@AuthenticationPrincipal CustomDetails user_info,
//		Model model) {
//
//	int rental_key_id = user_info.getUserList().getUser_id();
//	model.addAttribute("RentalBooks", rentalService.findRentalBook(rental_key_id));
//	return "rental";
//}
//
//@GetMapping("/list")
//public String showRentalList(Model model) {
//	model.addAttribute("RentalBooks", rentalService.findALLRentalBook());
//	return "rentallist";
//}

}
