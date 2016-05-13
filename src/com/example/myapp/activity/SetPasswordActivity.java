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

public class SetPasswordActivity extends Activity {
	
	private EditText first;
	private EditText second;
	private EditText problem;
	private EditText answer;
	private Button saveButton;
	private Button cancelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_password_layout);
		first = (EditText) findViewById(R.id.first_text);
		second = (EditText) findViewById(R.id.second_text);
		problem = (EditText) findViewById(R.id.problem_text);
		answer = (EditText) findViewById(R.id.answer_text);
        if (Password.hasPassword == true) 
        	initialize();
		saveButton = (Button) findViewById(R.id.certain_button);
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (first.getText().toString().equals(second.getText().toString())) {
					if (problem.getText().toString() != null) {
						if(answer.getText().toString() != null) {
							Password.hasPassword = true;
							Password.setPasswordEdit(first.getText().toString());
							Password.setPasswordProblem(problem.getText().toString());
							Password.setPasswordAnswer(answer.getText().toString());
							Toast.makeText(SetPasswordActivity.this, "设置密码成功", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent();
							intent.putExtra("return", "成功");
							setResult(RESULT_OK, intent);
							Log.v("return ok", "OK");
							finish();
						}
					}
				} else {
					Toast.makeText(SetPasswordActivity.this, "请检查输入", Toast.LENGTH_LONG).show();
				}
			}
		});
		cancelButton = (Button) findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Password.hasPassword = false;
				Toast.makeText(SetPasswordActivity.this, "取消密码成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.putExtra("return", "成功");
				setResult(RESULT_OK, intent);
				Log.v("return ok", "fail");
				finish();
				}
		});
	}

	@Override
	public void onBackPressed() {
		Intent intent  = new Intent();
		intent.putExtra("return", "Fail");
		setResult(RESULT_OK,intent);
		finish();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	//delete
	private void initialize() {
		first.setText(Password.getPasswordEdit());
		second.setText(Password.getPasswordEdit());
		problem.setText(Password.getPasswordProblem());
		answer.setText(Password.getPasswordAnswer());
	}

}
