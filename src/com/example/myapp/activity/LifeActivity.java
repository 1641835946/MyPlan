package com.example.myapp.activity;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.model.Life;
import com.example.myapp.util.CurrentTime;
import com.example.myapp.util.TimeApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class LifeActivity extends Activity {

	private EditText edit;
	
	private Button saveButton;
	
	private TimeDB timeDB = TimeDB.getInstance(TimeApplication.getContext());
	
	private Life life;
	
	private boolean needSave = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.life_layout);  
        edit = (EditText) findViewById(R.id.life_edit);
        life = timeDB.loadLife(CurrentTime.getTime());
        edit.setText(life.getMood());
        saveButton = (Button) findViewById(R.id.life_button);
        saveButton.setText("编辑");
        saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (saveButton.getText().toString() == "编辑") {
					//saveButton.getText().toString()  是编辑
					//可是saveButton.getText().toString() == "编辑"为false
					//不是用==，是用equals（）
					saveButton.setText("保存"); 
					edit.setFocusable(true);
					edit.setFocusableInTouchMode(true);
					edit.requestFocus();
					needSave = true;
				} else {
					if (saveButton.getText().toString() == "保存") 
					saveButton.setText("编辑");
					edit.setFocusable(false);
					saveButton.requestFocus();
					save();
					needSave = false;
				}
			}        	
        });
	}

	private void save() {
		life.setTime(CurrentTime.getTime());
		life.setMood(edit.getText().toString());
		if (timeDB.loadLife(CurrentTime.getTime()).getMood() == null) {
			timeDB.saveLife(life);
		}
		else {
			timeDB.updateLife(life);
		}	
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		if (needSave) {
			alertDialog();
			finish();
			Log.v("onBackPressed","save");
		}
	}
	
	@Override
	protected void onPause() {
		//super.onPause();
        if (needSave) {
			alertDialog();
			finish();
		}
        Log.v("onPause","save");
	}
	
	public void alertDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("提醒");
		dialog.setMessage("保存吗？");
		dialog.setCancelable(false);
		dialog.setPositiveButton("当然", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				save();
			}
		});
		dialog.setNegativeButton("不用", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
