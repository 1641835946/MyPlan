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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
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
					saveButton.setText("编辑");
					edit.setFocusable(false);
					saveButton.requestFocus();
					save();
					needSave = false;
				}
			}        	
        });
//        AlphaAnimation aa = new AlphaAnimation(0, 1);
//        aa.setDuration(1000);
//        saveButton.startAnimation(aa);
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
	
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
////		super.onBackPressed();
//		if (needSave) {
//			alertDialog();
//			finish();
//			Log.v("onBackPressed","save");
//		}
//		super.onBackPressed();
//	}
//	05-04 11:44:55.694: E/WindowManager(898): Activity com.example.myapp.activity.LifeActivity has leaked window com.android.internal.policy.impl.PhoneWindow$DecorView@410b3ce0 that was originally added here

	
//	@Override
//	protected void onPause() {
////		super.onPause();
//        if (needSave) {
//			alertDialog();
//			finish();
//		}
//        Log.v("onPause","save");
//	}
//	05-04 11:47:24.434: E/AndroidRuntime(945): android.app.SuperNotCalledException: Activity {com.example.myapp/com.example.myapp.activity.LifeActivity} did not call through to super.onPause()

//	@Override
//	protected void onPause() {
//        if (needSave) {
//			alertDialog();
//			finish();
//		}
//        super.onPause();
//        Log.v("onPause","save");
//	}
//	05-04 11:50:25.174: E/WindowManager(994): Activity com.example.myapp.activity.LifeActivity has leaked window com.android.internal.policy.impl.PhoneWindow$DecorView@410ab928 that was originally added here


	public void alertDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("提醒");
		dialog.setMessage("保存吗？");
		dialog.setCancelable(true);
		dialog.setPositiveButton("当然", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				save();
				finish();
			}
		});
		dialog.setNegativeButton("不用", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		dialog.show();
	}
	
//	public void verifyNeedSave() {
//		if (needSave) {   
//            alertDialog();     
//        }
//	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
         if (keyCode == KeyEvent.KEYCODE_BACK && needSave) {   
            alertDialog();   
            //return true;   
         }
         return super.onKeyDown(keyCode, event); 
    } 
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
