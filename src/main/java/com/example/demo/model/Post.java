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
	private String feel;
	
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

	public Post(String title, String content, String feel) {
		this(title, content);
		this.feel = feel;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		// 書き込み内容が「http://」または「https://」からはじまっていた場合はリンクを生成
		if (content.startsWith("http://") || content.startsWith("https://")) {
			return "<a href='" + content + "'>" + content + "</a>";
		}
		return content.replaceAll("\n", "<br />");
	}

	public String getCreatedAt() {
		return createdAt.format(FMT);
	}

	public static DateTimeFormatter getFmt() {
		return FMT;
	}

	public String getFeel() {
		return feel;
	}
	
}
