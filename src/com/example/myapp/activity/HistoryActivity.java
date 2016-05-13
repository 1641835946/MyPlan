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

	private List<HistoryItem> list = new ArrayList<HistoryItem>();// how to make
																	// it in the														// adapter

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_layout);
		list.add(new HistoryItem());//²»È»±¨´í
		TimeDB timeDB = TimeDB.getInstance(MyApplication.getContext());
		long minTime;
		minTime = timeDB.loadTime(1);// autoincrement from 1
		for (; minTime <= new CurrentTime().getTime(); minTime++) {
			HistoryItem historyItem = new HistoryItem();
			if (timeDB.loadSummary(minTime).getConclusion() != null) {
				historyItem.setSummary(timeDB.loadSummary(minTime)
						.getConclusion());
				long time = timeDB.loadSummary(minTime).getTime();
				String format = "20" + time / 10000 + "." + (time % 10000)
						/ 100 + "." + time % 100;// ((time % 100000000) / 10000)
													// + "" ;//+ "." + time %
													// 100;
				historyItem.setTimeFormat(format);
				Log.v("time", time + "");
				Log.v("time", "format" + format);
				list.add(historyItem);
			}
        }
		ListView listView = (ListView) findViewById(R.id.lv_list);
		ArrayAdapter<HistoryItem> adapter = new HistoryItemAdapter(
				HistoryActivity.this, R.layout.history_item, list);
		if (null == listView)
			Log.v("listview is null", "yes");
		listView.setAdapter(adapter);
	}

	private void test() {

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
