package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Post;
import com.example.demo.model.PostList;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Account account;
	
	@Autowired
	PostList postList;
	
	// 初期表示
	@GetMapping({"/", "/logout"})
	public String index() {
		session.invalidate();
		return "login";
	}
	
	// ログインボタンクリック時
	@PostMapping("login")
	public String login(
			@RequestParam("name") String name, 
			Model model) {
		// ログインチェック
		if (!(name.equals("田中太郎") || name.equals("鈴木一郎"))) {
			model.addAttribute("error", "ログインできませんでした");
			return "login";
		}
		
		// セッションスコープに登録されたアカウント情報にリクエストパラメータを設定
		account.setName(name);
		// 画面遷移
		return "blog";
	}
	
	@PostMapping("/blog")
	public String post(
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "content", defaultValue = "") String content,
			 Model model) {
		// 入力値のチェック
		if (title.isEmpty() && content.isEmpty()) {
			// 未入力の場合：エラーメッセージをスコープに登録
			model.addAttribute("error", "タイトルと書き込み内容を入力してください");
		}
		// セッションスコープから投稿のリストを取得
		List<Post> allPosts = postList.getPosts();
		// 受け取ったリクエストパラメータをリストに追加
		allPosts.add (new Post(title, content));
		// 画面遷移
		return "blog";
	}
	
}
