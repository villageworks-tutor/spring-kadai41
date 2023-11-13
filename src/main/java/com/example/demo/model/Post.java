package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Post {

	/**
	 * クラス定数：日付フォーマット文字列定数
	 */
	private final static DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * フィールド
	 */
	private String title;            // タイトル
	private String content;          // 内容
	private LocalDateTime createdAt; // 投稿日時
	
	/**
	 * コンストラクタ
	 * @param title   タイトル
	 * @param content 内容
	 */
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.createdAt = LocalDateTime.now();
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content.replaceAll("\n", "<br />");
	}

	public String getCreatedAt() {
		return createdAt.format(FMT);
	}
	
}
