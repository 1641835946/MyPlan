package com.example.myapp.activity;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.model.Summary;
import com.example.myapp.util.CurrentTime;
import com.example.myapp.util.TimeApplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.summary_layout);  
		//���˺þã������˺ܶ�ط������ŷ���©����һ�䣬������һ���edit=null��
		edit = (EditText) findViewById(R.id.summary_edit);
        summary = timeDB.loadSummary(CurrentTime.getTime());
        edit.setText(summary.getConclusion());
        saveButton = (Button) findViewById(R.id.summary_button);
        saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (saveButton.getText().toString() != "����") {
					//saveButton.getText().toString()  �Ǳ༭
					//����saveButton.getText().toString() == "�༭"Ϊfalse
					saveButton.setText("����"); 
					edit.setFocusable(true);
					edit.setFocusableInTouchMode(true);
					edit.requestFocus();
				} else {
					saveButton.setText("�༭");
					edit.setFocusable(false);
					saveButton.requestFocus();
					summary.setTime(CurrentTime.getTime());
					summary.setConclusion(edit.getText().toString());
					save();
				}
			}        	
        });
	}

	private void save() {
		if (timeDB.loadSummary(CurrentTime.getTime()).getConclusion() == null) {
			timeDB.saveSummary(summary);
		}
		else {
			timeDB.updateSummary(summary);
		}	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
