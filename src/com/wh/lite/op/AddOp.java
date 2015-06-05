package com.wh.lite.op;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.crud.DataSupport;

import android.util.Log;

import com.wh.bean.Comment;
import com.wh.bean.News;

/**
 * 添加操作
 * 
 * @author Administrator
 *
 */
public class AddOp {

	public static void addSingle() {
		// 添加一条新闻
		News news = new News();
		news.setTitle("这是一条新闻标题");
		news.setContent("这是一条新闻内容");
		news.setPublishDate(new Date());
		if (news.save()) {
			Log.d("TAG", "添加成功");
		} else {
			Log.d("TAG", "添加失败");
		}

		// 或得保存后记录的id
		Log.d("TAG", "news id is " + news.getId());

		// saveThrows()方法来存储数据，
		// 一旦存储失败就会抛出一个DataSupportException异常，
		// 我们可以通过对这个异常进行捕获来处理存储失败的情况。
		// news.saveThrows();
	}

	/***
	 * 添加有关联的
	 * 
	 * 先是存储了一条comment1数据，然后存储一条comment2数据，
	 * 接着在存储News之前先把刚才的两个Comment对象添加到了News的commentList列表当中
	 * ，这样就表示这两条Comment是属于这个News对象的，最后再把News存储到数据库中，这样它们之间的关联关系就会自动建立了
	 */
	public static void addMul() {

		// 新添加关联记录
		for (int i = 0; i < 100; i++) {
			Comment comment1 = new Comment();
			comment1.setContent("好评！");
			comment1.setPublishDate(new Date());
			comment1.save();

			Comment comment2 = new Comment();
			comment2.setContent("赞一个");
			comment2.setPublishDate(new Date());
			comment2.save();

			News news = new News();
			news.getCommentList().add(comment1);
			news.getCommentList().add(comment2);
			news.setTitle("第二条新闻标题");
			news.setContent("第二条新闻内容");
			news.setPublishDate(new Date());
			news.setCommentCount(news.getCommentList().size());
			news.save();
		}

		/***
		 * id为3的新闻已存在评论数，需要在添加评论
		 */

		News news3 = DataSupport.find(News.class, 3);

		Comment comment3 = new Comment();
		comment3.setContent("添加评论3！");
		comment3.setPublishDate(new Date());
		comment3.setNews(news3);
		comment3.save();

		Comment comment4 = new Comment();
		comment4.setContent("添加评论4");
		comment4.setPublishDate(new Date());
		comment4.setNews(news3);
		comment4.save();

		List<Comment> comments = news3.getCommentList();
		comments.add(comment3);
		comments.add(comment4);
		news3.setCommentCount(comments.size());
		news3.save();

	}

	/***
	 * 一次性保存多条
	 */
	public static void addList() {
		List<News> newsList = new ArrayList<News>();

		News news1 = new News();
		news1.setTitle("这是一条新闻标题");
		news1.setContent("这是一条新闻内容");
		news1.setPublishDate(new Date());

		News news2 = new News();
		news2.setTitle("这是一条新闻标题");
		news2.setContent("这是一条新闻内容");
		news2.setPublishDate(new Date());

		newsList.add(news1);
		newsList.add(news2);

		DataSupport.saveAll(newsList);
	}
}
