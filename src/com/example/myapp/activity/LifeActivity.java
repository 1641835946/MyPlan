package com.example.myapp.activity;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.model.Life;
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

public class LifeActivity extends Activity {

	private EditText edit;
	
	private Button saveButton;
	
	private TimeDB timeDB = TimeDB.getInstance(TimeApplication.getContext());
	
	private Life life;
	
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
        saveButton.setText("±à¼­");
        saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (saveButton.getText().toString() != "±£´æ") {
					//saveButton.getText().toString()  ÊÇ±à¼­
					//¿ÉÊÇsaveButton.getText().toString() == "±à¼­"Îªfalse
					saveButton.setText("±£´æ"); 
					edit.setFocusable(true);
					edit.setFocusableInTouchMode(true);
					edit.requestFocus();
				} else {
					if (saveButton.getText().toString() == "±£´æ") 
						System.out.println("true");
					saveButton.setText("±à¼­");
					edit.setFocusable(false);
					saveButton.requestFocus();
					life.setTime(CurrentTime.getTime());
					life.setMood(edit.getText().toString());
					save();
				}
			}        	
        });
	}

	private void save() {
		if (timeDB.loadLife(CurrentTime.getTime()).getMood() == null) {
			timeDB.saveLife(life);
		}
		else {
			timeDB.updateLife(life);
		}	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
