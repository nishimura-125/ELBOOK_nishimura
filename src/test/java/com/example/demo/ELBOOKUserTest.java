package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.UserList;
import com.example.demo.Entity.UserList.Authority;
import com.example.demo.Service.UserService;

@SpringBootTest
public class ELBOOKUserTest {

	@Autowired
	private UserService userService;

	//@Disabledをつけることでテストスキップが行える
	@Test
	@Disabled
	//全件取得処理のテストコード
	void findAllTest() {
		//count の値を変えてください
		int count = 2;
		List<UserList> userLists = userService.findUser();
		System.out.println(userLists.size());
		assertEquals(count, userLists.size());
	}

	@Test
	@Disabled
	void targetUser() {
		int select_num = 15;
		UserList userList = userService.targetUser(select_num);
		assertEquals(15, userList.getUser_id());
		assertEquals("city", userList.getUser_name());
		assertEquals("test@com", userList.getMail());
		assertEquals("83f774917979f41ed9ed59ee5e206360e21b8027337f206dfbedb11dcd35cbec5554124d2919bbf6", userList.getPass());
	}

	@Test
	@Disabled
	//ユーザー追加処理ADMIN権限を付与して作成
	void createUserAdmin() {
		System.out.println("テスト開始");
		//DBへテストデータを登録する
		String name = "takemiya";
		UserList userList = new UserList();
		userList.setUser_name(name);
		userList.setMail(name + "@com");
		userList.setPass("password");
		userList.setAuthority(Authority.ADMIN);

		System.out.println("----ユーザー登録処理開始----");
		System.out.println("ユーザー名：" + userList.getUser_name());
		System.out.println("メールアドレス：" + userList.getMail());
		System.out.println("権限：" + userList.getAuthority());
		userService.createUser(userList);
		System.out.println("----ユーザー登録処理終了----");

		//DBへ再度問い合わせを行い登録したデータの整合性をテスト

		System.out.println("----ユーザー登録処理情報取得開始----");
		UserList resultList = userService.emailFindUser(name + "@com");
		System.out.println("ユーザー名：" + resultList.getUser_name());
		System.out.println("メールアドレス：" + resultList.getMail());
		System.out.println("権限：" + resultList.getAuthority());
		assertEquals(name, resultList.getUser_name());
		assertEquals(name + "@com", resultList.getMail());
		assertEquals(Authority.ADMIN, resultList.getAuthority());
		System.out.println("----ユーザー登録処理情報取得終了----");

		//登録したテストデータの削除
		System.out.println("----ユーザー登録情報削除開始----");
		userService.emailDeleteUesr(name + "@com");
		System.out.println("----ユーザー登録情報削除終了----");

		System.out.println("テスト正常終了");
	}

	@Test
	@Disabled
	//ユーザー追加処理
	//ユーザー追加処理ADMIN権限を付与して作成
	void createUserUser() {
		System.out.println("テスト開始");
		//DBへテストデータを登録する
		String name = "ninomiya";
		UserList userList = new UserList();
		userList.setUser_name(name);
		userList.setMail(name + "@com");
		userList.setPass("password");
		userList.setAuthority(Authority.USER);

		System.out.println("----ユーザー登録処理開始----");
		System.out.println("ユーザー名：" + userList.getUser_name());
		System.out.println("メールアドレス：" + userList.getMail());
		System.out.println("権限：" + userList.getAuthority());
		userService.createUser(userList);
		System.out.println("----ユーザー登録処理終了----");

		//DBへ再度問い合わせを行い登録したデータの整合性をテスト
		System.out.println("----ユーザー登録処理情報取得開始----");
		UserList resultList = userService.emailFindUser(name + "@com");

		System.out.println("ユーザー名：" + resultList.getUser_name());
		System.out.println("メールアドレス：" + resultList.getMail());
		System.out.println("権限：" + resultList.getAuthority());

		assertEquals(name, resultList.getUser_name());
		assertEquals(name + "@com", resultList.getMail());
		assertEquals(Authority.USER, resultList.getAuthority());

		//登録したテストデータの削除
		System.out.println("----ユーザー登録情報削除開始----");
		userService.emailDeleteUesr(name + "@com");
		System.out.println("----ユーザー登録情報削除終了----");

		System.out.println("テスト正常終了");
	}

	@Test
	@Disabled
	void editUser() {
		UserList userList = new UserList();
		userList.setUser_id(6);
		userList.setUser_name("kamikita");
		userList.setMail("kamikita@com");
		userList.setPass("password");
		userService.editUser(userList);
		assertEquals("kamikita", userList.getUser_name());
		assertEquals("kamikita@com", userList.getMail());
	}

	@Test
	@Disabled
	//ユーザーの削除処理のテストコード
	//deleteflg の上書きを行う為一部処理の変更が必要

	//Todo target user検索においてデリートフラグも取得できるようにする
	void deleteUser() {
		//引数を変えてください
		userService.deleteUser(16);
	}

}
