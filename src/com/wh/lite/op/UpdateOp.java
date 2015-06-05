package com.wh.lite.op;

import org.litepal.crud.DataSupport;

import android.content.ContentValues;

import com.wh.bean.News;

/***
 * 修改操作
 * 
 * @author Administrator
 *
 */
public class UpdateOp {

	/**
	 * 将id为2的新闻的标题改为 今日iPhone6发布
	 */
	public static void updateById() {
		ContentValues values = new ContentValues();
		values.put("title", "今日iPhone6发布");
		DataSupport.update(News.class, values, 2);

		// 第二种更改方法，不需要ContentValues
		News updateNews = new News();
		updateNews.setTitle("今日iPhone6发布");
		updateNews.update(2);
	}

	/**
	 * 把news表中标题为“今日iPhone6发布”的所有新闻的标题改成“今日android5.0发布”
	 */
	public static void updateByCondition() {
		ContentValues values = new ContentValues();
		values.put("title", "今日android5.0发布");
		DataSupport.updateAll(News.class, values, "title = ?", "今日iPhone6发布");

		// 第二种更改方法，不需要ContentValues
		News updateNews = new News();
		updateNews.setTitle("今日iPhone6发布");
		updateNews.updateAll("title = ? and commentcount > ?", "今日iPhone6发布",
				"0");

		/**
		 * 种用法有一点需要注意，就是如果我们想把某一条数据修改成默认值，比如说将评论数修改成0，只是调用updateNews.
		 * setCommentCount
		 * (0)这样是不能修改成功的，因为即使不调用这行代码，commentCount的值也默认是0。所以如果想要将某一列的数据修改成默认值的话
		 * ，还需要借助setToDefault
		 * ()方法。用法也很简单，在setToDefault()方法中传入要修改的字段名就可以了(类中的字段名)，
		 * 比如说我们想要把news表中所有新闻的评论数清零，就可以这样写：
		 */

		News updateNews1 = new News();
		updateNews1.setToDefault("commentCount");
		updateNews1.updateAll();
	}

	/**
	 * 
	 * 个conditions数组，由于它的类型是一个String数组，我们可以在这里填入任意多个String参数，
	 * 其中最前面一个String参数用于指定约束条件
	 * ，后面所有的String参数用于填充约束条件中的占位符(即?号)，比如约束条件中有一个占位符，那么后面就应该填写一个参数
	 * ，如果有两个占位符，后面就应该填写两个参数，以此类推。 比如说我们想把
	 * 把news表中标题为“今日iPhone6发布”的所有新闻的标题改成“今日android5.0发布”
	 */
	public static void updateByMoreCondition() {
		ContentValues values = new ContentValues();
		values.put("title", "今日android5.0发布");
		DataSupport.updateAll(News.class, values,
				"title = ? and commentcount > ?", "今日iPhone6发布", "0");
	}

	/**
	 * 修改所有 把news表中所有新闻的标题都改成“android5.0发布”
	 */
	public static void updateAll() {
		ContentValues values = new ContentValues();
		values.put("title", "android5.0发布");
		DataSupport.updateAll(News.class, values);
	}

}
