package com.wh.ac;

import android.app.Activity;
import android.os.Bundle;

import com.wh.lite.op.AddOp;
import com.wh.lite.op.FuctionOp;
import com.wh.lite.op.QueryOp;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// AddOp.addSingle();
		AddOp.addMul();

		// AddOp.addList();
		//
		// News news = new News();
		// news.setTitle("这是一条新闻标题");
		// news.setContent("这是一条新闻内容");
		// news.setPublishDate(new Date());

		// if (news.isSaved()) {
		// Log.i("TAG", "已经保存过");
		// }

		QueryOp.findById();

		QueryOp.findFirst();

		QueryOp.findByCondition();

		FuctionOp.function();
	}

}
