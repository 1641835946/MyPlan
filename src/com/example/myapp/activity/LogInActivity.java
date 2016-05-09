package com.example.myapp.activity;

import com.example.myapp.R;
import com.example.myapp.model.Password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity {//setsetset
	
	private EditText inputEdit;
	
	private Button loginButton;
	
	private Button forgetButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (Password.hasPassword == true) {
//			setContentView(R.layout.login_layout);
		} else {
			Intent intent = new Intent(this, PlanActivity.class);
			startActivity(intent);
			finish();
		}
		setContentView(R.layout.login_layout);
		inputEdit = (EditText) findViewById(R.id.input_edit);
		loginButton = (Button) findViewById(R.id.login_button);
		forgetButton = (Button) findViewById(R.id.forget_button);
		if (loginButton == null)
			Log.v("loginbutton", "isnull");
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (inputEdit.getText().toString().equals(Password.getPasswordEdit())) {
					Intent intentPlanActivity = new Intent(LogInActivity.this, PlanActivity.class);
					startActivity(intentPlanActivity);
				} else {
					Toast.makeText(LogInActivity.this, "«Î÷ÿ–¬ ‰»Î√‹¬Î", Toast.LENGTH_SHORT).show();
				}
			}
		});
		forgetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent reset = new Intent(LogInActivity.this, ResetActivity.class);
				startActivity(reset);
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
