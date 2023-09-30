package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.BookList;
import com.example.demo.Service.BookService;

@SpringBootTest
public class ELBOOKBookTest {

	@Autowired
	private BookService bookService;

	//件数取得処理のテスト
	@Test
	@Disabled
	void findBookTest() {

		//DBの件数を入力して一致しているかどうか？
		int count = 2;
		List<BookList> lists = bookService.findBook();
		System.out.println(lists.size());
		assertEquals(count, lists.size());

	}

	//book_idを使用して検索の一致を確認する
	@Test
	@Disabled
	void targetBookTest() {
		//	BookList lists = bookService.targetBook(10);

	}

	@Test
	@Disabled
	void createBook() {
		BookList bookList = new BookList();
		bookList.setBook_name("じゃんけんで５０連勝した人の末路");
		bookList.setBook_author("はせきょん");
		bookList.setBook_publisher("神山舎");
		bookList.setBook_releasetime("2022-11-11");
		bookService.createBook(bookList);

		//BookList targetlistBookLists = bookService.targetBook(13);

	}

//	BOOKList
	@Test
	@Disabled
	void editBook() {
		BookList bookList = new BookList();
		bookList.setBook_id(12);
		bookList.setBook_name("スッキリわかるJava入門実践編");
		bookList.setBook_author("あああ");
		bookList.setBook_publisher("aaa");
		bookList.setBook_releasetime("2023-06-24");

		bookService.editBook(bookList);

	}

//	削除処理
	@Test
	@Disabled
	void deleteBook() {
		bookService.deleteBook(13);
	}

	
//	在庫数
	@Test
	@Disabled
	void rentalreturnBook() {
		int book_id = 12;
		BookList lists = bookService.targetBook(book_id);
		System.out.println("在庫数" + lists.getStock());

		bookService.rentalBook(book_id);
		BookList rentald_lists = bookService.targetBook(book_id);
		System.out.println("貸し出し処理在庫数" + rentald_lists.getStock());

		System.out.println(lists.getStock() - 1);

		assertEquals(lists.getStock() - 1, rentald_lists.getStock());
		System.out.println("-----------------------------------");

		bookService.returnBook(book_id);
		BookList returnd_lists = bookService.targetBook(book_id);
		System.out.println("返却処理在庫数" + returnd_lists.getStock());

		assertEquals(lists.getStock(), returnd_lists.getStock());
		System.out.println("-----------------------------------");

	}
}
