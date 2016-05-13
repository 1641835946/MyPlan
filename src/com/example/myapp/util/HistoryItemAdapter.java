package com.example.myapp.util;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryItemAdapter extends ArrayAdapter<HistoryItem> {
	
	private int resourceId;
	
	public HistoryItemAdapter(Context context, int textViewResourceId, List<HistoryItem> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HistoryItem historyItem = getItem(position);
		View view;
		ViewHolder viewHolder ;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.summary = (TextView) view.findViewById(R.id.txt_date_content);
			viewHolder.time = (TextView) view.findViewById(R.id.txt_date_time);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.summary.setText(historyItem.getSummary());
		viewHolder.time.setText(historyItem.getTimeFormat());
		if (historyItem.getSummary() == null) {
			view.setVisibility(View.GONE);//只打GONE不行。
		} else {
			view.setVisibility(View.VISIBLE);
		}
		return view;
	}
	
	class ViewHolder {
		
		TextView summary;
		TextView time;
	}

}
