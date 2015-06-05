package com.wh.lite.op;

import org.litepal.crud.DataSupport;

import com.wh.bean.News;

/**
 * 删除操作
 * 
 * @author Administrator
 *
 */
public class DeleteOp {

	/**
	 * 删除news表中id为2的记录
	 * 
	 * 这不仅仅会将news表中id为2的记录删除，同时还会将其它表中以news id为2的这条记录作为外键的数据一起删除掉
	 */
	public static void deleteById() {
		DataSupport.delete(News.class, 2);

		// 另一种删除方法
		// 这个对象一定是要持久化之后的，一个非持久化的对象如果调用了delete()方法则不会产生任何效果
		// News news = new News();
		// news.delete();

		//
		News news = new News();
		news.setTitle("这是一条新闻标题");
		news.setContent("这是一条新闻内容");
		news.save();

		news.delete();

		/**
		 * 判断一个对象是否是持久化之后的，DataSupport类中提供了一个isSaved()方法，这个方法返回true就表示该对象是经过持久化的
		 * ，返回false则表示该对象未经过持久化。那么删除一个对象
		 */

		if (news.isSaved()) {
			news.delete();
		}

	}

	/**
	 * 把news表中标题为“今日iPhone6发布”且评论数等于0的所有新闻都删除掉
	 */
	public static void deleteByConditon() {
		DataSupport.deleteAll(News.class, "title = ? and commentcount = ?",
				"今日iPhone6发布", "0");
	}

	/***
	 * 把news表中所有的数据全部删除掉
	 */
	public static void deleteAll() {
		DataSupport.deleteAll(News.class);
	}

}
