package com.example.myapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.util.CurrentTime;
import com.example.myapp.util.HistoryItem;
import com.example.myapp.util.HistoryItemAdapter;
import com.example.myapp.util.ItemAdapter;
import com.example.myapp.util.MyApplication;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class HistoryActivity extends Activity {

	private List<HistoryItem> list = new ArrayList<HistoryItem>();
	//这里不能少，没有初始化直接add是null会报错的；
	// how to make it in the adapter

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_layout);
		//list.add(new HistoryItem());//不然报错
//		TimeDB timeDB = TimeDB.getInstance(MyApplication.getContext());
//		？这里本来有数据读取，现在这里用的是测试数据
		//java.lang.NumberFormatException: Invalid long: "null"
		//反正不知道是怎么回事
		test();
		ListView listView = (ListView) findViewById(R.id.lv_list);
		ArrayAdapter<HistoryItem> adapter = new HistoryItemAdapter(
				HistoryActivity.this, R.layout.history_item, list);
		if (null == listView)
			Log.v("listview is null", "yes");
		listView.setAdapter(adapter);
	}

	private List<HistoryItem> test() {
		for (int i = 0; i<10; i++) {
			HistoryItem item = new HistoryItem();
			item.setSummary("这里是测试数据：" + i + "-" + i);
			item.setTimeFormat("2015"+"."+ i + "." +i);
			list.add(item);
		}
		return list;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
