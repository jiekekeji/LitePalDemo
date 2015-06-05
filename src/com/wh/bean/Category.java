package com.wh.bean;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

/**
 * 类别
 * 
 * @author Administrator
 *
 */
public class Category extends DataSupport{
	private int id;

	private String name;

	/** 类别与新闻多对多 **/
	private List<News> newsList = new ArrayList<News>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
}
