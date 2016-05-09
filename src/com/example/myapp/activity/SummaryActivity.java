package com.example.myapp.activity;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.model.Summary;
import com.example.myapp.util.CurrentTime;
import com.example.myapp.util.TimeApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;



public class SummaryActivity extends Activity {

	private EditText edit;
	
	private Button saveButton;
	
	private TimeDB timeDB = TimeDB.getInstance(TimeApplication.getContext());
	
	private Summary summary;
	
	private boolean needSave = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.summary_layout);  
		//找了好久，怀疑了很多地方，最后才发现漏了这一句，导致下一句的edit=null；
		edit = (EditText) findViewById(R.id.summary_edit);
        summary = timeDB.loadSummary(CurrentTime.getTime());
        edit.setText(summary.getConclusion());
        saveButton = (Button) findViewById(R.id.summary_button);
        saveButton.setText("编辑");
        saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (saveButton.getText().toString() == "编辑") {
					//saveButton.getText().toString()  是编辑
					//可是saveButton.getText().toString() == "编辑"为false
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
	}

	private void save() {
		summary.setTime(CurrentTime.getTime());
		summary.setConclusion(edit.getText().toString());
		if (timeDB.loadSummary(CurrentTime.getTime()).getConclusion() == null) {
			timeDB.saveSummary(summary);
		}
		else {
			timeDB.updateSummary(summary);
		}	
	}
	
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
