package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.AppConfig;
import com.example.demo.Entity.BookList;
import com.example.demo.Service.BookService;

import lombok.Data;

@Controller
@Data
@RequestMapping("/book")
public class BookController {
//
	@Autowired
	BookService bookService;

	@Autowired
	private AppConfig appConfig;
	
	@GetMapping("/list")
	public String findBook(Model model) {
		model.addAttribute("BookList",bookService.findBook());
		return "booklist";
	}
	
	@GetMapping("/bookinfo")
	public String showEditBook(Model model) {
		model.addAttribute("BookList", bookService.findBook());
		return "bookinfo";
	}

	@PostMapping("/bookcreate")
	public String createBook(@ModelAttribute("createBook") BookList bookList, Model model) {

		//ファイル名の決定のためDBのレコード数を取得(book_idにしようと思ったがユニークキーが存在しないため不可能)
		int max_number_book = bookService.getMaxNumberBook();

		String tmp_file_name = String.valueOf(max_number_book + 1) + ".jpg";

		bookList.setFile_name(tmp_file_name);
		//System.out.println(bookList);
		//DBに登録処理
		bookService.createBook(bookList);

		//		System.out.println(file);
		//ファイルの保存処理
		if (bookList.getFile().isEmpty()) {
			model.addAttribute("error", "ファイルを指定してください");
			return "imagetest";
		}
		File dest = new File(appConfig.getImageDir(), tmp_file_name);
		System.out.println(dest);
		try {
			bookList.getFile().transferTo(dest); //表示される修正候補の「try/catchで囲む」を選択
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "redirect:/book/list";
	}
	
	@GetMapping("/createview")
	public String showBookcreatview(Model model) {
		return "bookcreate";
	}
	
	@GetMapping("/editview/{book_id}")
	public String moveEditView(@PathVariable ("book_id") int book_id, Model model) {
		model.addAttribute("TargetBook", bookService.targetBook(book_id));
		return "bookedit";
	}

	
	@GetMapping("/bookdelete/{book_id}")
	public String deleteBook(@PathVariable("book_id") int book_id) {
		bookService.deleteBook(book_id);
		return "redirect:/book/bookinfo";
	}
}