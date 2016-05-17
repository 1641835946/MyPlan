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
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends Activity {
	
	private TextView problemHint;
	private EditText answerProblem;
	private Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_layout);
		problemHint = (TextView) findViewById(R.id.problem_hint);
		answerProblem = (EditText) findViewById(R.id.answer_problem);
		submit = (Button) findViewById(R.id.submit);
		problemHint.setText(Password.getPasswordProblem());
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (answerProblem.getText().toString().equals(Password.getPasswordAnswer())) {
					Intent intent = new Intent(ResetActivity.this, SetPasswordActivity.class);
					startActivityForResult(intent, 1);
				} else {
					Toast.makeText(ResetActivity.this, "错误，请重新输入", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			Log.v("onActivityResult", "doing");
			if(resultCode == RESULT_OK) {//problem				
				Log.v("return", data.getStringExtra("return"));
				if (data.getStringExtra("return").equals("成功"))
				  finish();
			}
		default:
		}
	}

}
