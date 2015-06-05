package com.wh.bean;

import java.util.Date;

import org.litepal.crud.DataSupport;

/**
 * 评论
 * 
 * @author Administrator
 *
 */
public class Comment extends DataSupport {

	private int id;

	private String content;

	private Date publishDate;

	/** 多条评论对应一条新闻 **/
	private News news;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
}
