package com.example.myapp.util;

import com.example.myapp.R;
import com.example.myapp.activity.LifeActivity;
import com.example.myapp.activity.PlanActivity;
import com.example.myapp.activity.SummaryActivity;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleLayout extends LinearLayout{

//	public Button titlePlan = (Button) findViewById(R.id.title_plan);
//	public Button titleLife = (Button) findViewById(R.id.title_life);
//	public Button titleSummary = (Button) findViewById(R.id.title_summary);
//05-02 14:01:42.596: E/AndroidRuntime(598): java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.myapp/com.example.myapp.activity.PlanActivity}: android.view.InflateException: Binary XML file line #9: Error inflating class com.example.myapp.util.TitleLayout	
	public TitleLayout(Context context,AttributeSet attrs) {
		super(context, attrs);
		final Context mContext = context;
		LayoutInflater.from(context).inflate(R.layout.title, this);
		final Button titlePlan = (Button) findViewById(R.id.title_plan);
		final Button titleLife = (Button) findViewById(R.id.title_life);
		final Button titleSummary = (Button) findViewById(R.id.title_summary);
		titlePlan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
            //change color
			//	titlePlan.setTextColor();
				//titlePlan.setTextColor("#90ee90");
				Intent intent = new Intent(mContext, PlanActivity.class);
				mContext.startActivity(intent);
			}
		});
		titleLife.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, LifeActivity.class);
				mContext.startActivity(intent);
			}
		});
		titleSummary.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, SummaryActivity.class);
				mContext.startActivity(intent);
			}
		});
	}
}
