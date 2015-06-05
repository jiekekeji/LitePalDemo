package com.wh.lite.op;

import java.util.List;

import org.litepal.crud.DataSupport;

import android.database.Cursor;
import android.util.Log;

import com.wh.bean.Comment;
import com.wh.bean.News;

/**
 * 查询操作
 * 
 * @author Administrator
 *
 */
public class QueryOp {

	/**
	 * 查询id为1的新闻
	 */
	public static void findById() {
		News news = DataSupport.find(News.class, 1);
		Log.i("TAG", "id为1的新闻标题是:" + news.getTitle());

		// 根据id查询多条记录
		List<News> newsList2 = DataSupport.findAll(News.class, 2, 6, 9);
		for (News news2 : newsList2) {
			Log.i("TAG", "根据多个id查询多条记录" + news2.getId());
		}

		// 根据id数组查询
		long[] ids = new long[] { 1, 3, 5, 7 };
		List<News> newsList3 = DataSupport.findAll(News.class, ids);
		for (News news3 : newsList3) {
			Log.i("TAG", "根据id数组查询多条记录" + news3.getId());
		}

		// 查询所有
		List<News> newsList4 = DataSupport.findAll(News.class);
		for (News news4 : newsList4) {
			Log.i("TAG", "查询所有" + news4.getId());
		}

	}

	/**
	 * 查询表中的最后和第一条记录
	 */
	public static void findFirst() {

		News firstNews = DataSupport.findFirst(News.class);
		Log.i("TAG", "表中的第一条记录的id==" + firstNews.getId());

		News lastNews = DataSupport.findLast(News.class);
		Log.i("TAG", "表中的最后一条条记录的id==" + lastNews.getId());

	}

	/**
	 * 根据条件查询
	 */
	public static void findByCondition() {
		// 查询news表中所有评论数大于零的新闻

		/**
		 * where()方法接收任意个字符串参数，其中第一个参数用于进行条件约束，从第二个参数开始，都是用于替换第一个参数中的占位符的。
		 * 那这个where()方法就对应了一条SQL语句中的where部分
		 */
		List<News> newsList = DataSupport.where("commentcount > ?", "0").find(
				News.class);
		Log.i("TAG", "评论数大于零的新闻有" + newsList.size() + "条");

		// 根据指定的指定条件查询指定的列

		/**
		 * 只要title和content这两列数据
		 */
		List<News> newsList1 = DataSupport.select("title", "content")
				.where("commentcount > ?", "0").find(News.class);
		for (News news1 : newsList1) {

		}

		// 将查询出的新闻按照发布的时间倒序排列，即最新发布的新闻放在最前面

		List<News> newsList2 = DataSupport
				.select("title", "content", "publishDate")
				.where("commentcount > ?", "0").order("publishdate desc")
				.find(News.class);
		for (News news : newsList2) {
			Log.i("TAG", "按发布时间排序" + news.getPublishDate());
		}

		// 分页查询

		/**
		 * 翻到第二页时，展示第11到第20条新闻
		 * 
		 * offset(10):从第11条开始 limit(10)：取10条
		 * 
		 * 从满足条件中的记录从第一条开始取一条
		 */

		List<News> newsList3 = DataSupport.select("title", "content")
				.where("commentcount > ?", "0").order("publishdate desc")
				.limit(1).offset(0).find(News.class);

		if (newsList3 != null) {

			Log.i("TAG", newsList3.size() + "");
		}

		for (News news : newsList3) {
			Log.i("TAG", "分页查询" + news.getTitle());
		}

		/***
		 * 
		 * 上述我们的所有用法中，都只能是查询到指定表中的数据而已，关联表中数据是无法查到的，因为LitePal默认的模式就是懒查询
		 * 
		 * 
		 */

		// /查询news表中id为1的新闻，并且把这条新闻所对应的评论也一起查询出来
		News news = DataSupport.find(News.class, 3, true);
		List<Comment> commentList = news.getCommentList();

		for (Comment comment : commentList) {
			Log.i("TAG", "id为3的所有评论内容" + comment.getContent());
		}

		// 原生查询

		/***
		 * findBySQL()方法接收任意个字符串参数，其中第一个参数就是SQL语句，后面的参数都是用于替换SQL语句中的占位符的，用法非常简单。
		 * 另外，findBySQL()方法返回的是一个Cursor对象，这和原生SQL语句的用法返回的结果也是相同的。
		 * 
		 * 
		 */

		Cursor cursor = DataSupport.findBySQL(
				"select * from news where commentcount>?", "0");
		while (cursor.moveToNext()) {
			Log.i("TAG", "新闻标题==" + cursor.getString(2));
		}
	}

}
