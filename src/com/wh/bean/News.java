package com.wh.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

/**
 * 新闻实体
 * 
 * @author Administrator
 *
 */
public class News extends DataSupport {

	private int id;
	/** 标题 **/
	private String title;
	/** 内容 **/
	private String content;
	/** 分发日期 **/
	private Date publishDate;

	private int commentCount;

	/** 一条新闻对应一条摘要 **/
	private Introduction introduction;

	/** 一条新闻有多条评论 **/
	private List<Comment> commentList = new ArrayList<Comment>();

	/** 新闻和类别多对多 **/
	private List<Category> categoryList = new ArrayList<Category>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Introduction getIntroduction() {
		return introduction;
	}

	public void setIntroduction(Introduction introduction) {
		this.introduction = introduction;
	}

	public List<Comment> getCommentList() {
		// 懒加载，当需要得到某条新闻的评论时，才根据新闻id加载相应评论
		return DataSupport.where("news_id = ?", String.valueOf(id)).find(
				Comment.class);
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
