package com.example.myapp.activity;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.model.Life;
import com.example.myapp.util.CurrentTime;
import com.example.myapp.util.MyEditText;
import com.example.myapp.util.MyApplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LifeActivity extends Activity {

	public static final int CHOOSE_PHOTO = 1;
	
	private MyEditText edit;
	
	private Button saveButton;
	
	private TimeDB timeDB = TimeDB.getInstance(MyApplication.getContext());
	
	private Life life;
	
	private boolean needSave = false;
	
	private Button addPhoto;
	
	private Button takePhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.life_layout);  
        edit = (MyEditText) findViewById(R.id.life_edit);
        life = null;
        life = timeDB.loadLife(new CurrentTime().getTime());
        //life.setMood("nothing");
        //edit.setText(life.getMood());
        getImagePath(life.getMood());
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
        
        takePhoto = (Button) findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}      	
        });
        addPhoto = (Button) findViewById(R.id.add_photo);
        addPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveButton.setText("保存");
				Intent addPhotoIntent = new Intent("android.intent.action.GET_CONTENT");
				addPhotoIntent.setType("image/*");
				startActivityForResult(addPhotoIntent, CHOOSE_PHOTO);
			}
        });
//        AlphaAnimation aa = new AlphaAnimation(0, 1);
//        aa.setDuration(1000);
//        saveButton.startAnimation(aa);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case CHOOSE_PHOTO: 
			if (resultCode == RESULT_OK) {
				if (Build.VERSION.SDK_INT >= 19) {
					handleImageOnKitKat(data);
				} else {
					handleImageBeforeKitKat(data);
				}
			}
			break;
		default:
			break;
		}
	}
	
	@TargetApi(19)
    private void handleImageOnKitKat(Intent data){
    	String imagePath=null;
    	Uri uri=data.getData();
    	if(DocumentsContract.isDocumentUri(this,uri)){
    		String docId=DocumentsContract.getDocumentId(uri);
    		if("com.android.providers.media.documents".equals(uri.getAuthority())){
    			String id=docId.split(":")[1];
    			String selection=MediaStore.Images.Media._ID+"="+id;
    			imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
    		}else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
    			Uri contentUri=ContentUris.withAppendedId(Uri.parse("cintent://downloads/public_downloads"),Long.valueOf(docId));
    			imagePath=getImagePath(contentUri,null);
    		}	
    	}else if("content".equalsIgnoreCase(uri.getScheme())){
    		imagePath=getImagePath(uri,null);
    	}
    	displayImage(imagePath);
    }
    
    private void handleImageBeforeKitKat(Intent data){
    	Uri uri=data.getData();
    	String imagePath=getImagePath(uri,null);
    	displayImage(imagePath);
    }
    
    private String getImagePath(Uri uri,String selection){
    	String path=null;
    	Cursor cursor=getContentResolver().query(uri, null,selection,null,null);
    	if(cursor!=null){
    		if(cursor.moveToFirst()){
    			path=cursor.getString(cursor.getColumnIndex(Media.DATA));
    		}
    		cursor.close();
    	}
    	return path;
    }
    
    private void getImagePath(String text) {
    	if (!TextUtils.isEmpty(text)) {
    		String[] array = text.split("\\|");
    		if (array != null) {
    			int i = 0;
    			while (i < array.length) {
    				if (!array[i].isEmpty()) {
    					if ((array[i].substring(0, 3)).equals("img")) {
        					displayImage(array[i].substring(3));
        				} else edit.append(array[i]);
    				}
    				i++;
    			}
    		}
    	}
    }
    
    private void displayImage(String imagePath){
    	if(imagePath!=null){
    		Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
    		edit.displayBitmapOnText(bitmap, edit, imagePath);
    	}else{
    		Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
    	}
    }


	private void save() {
		life.setTime(new CurrentTime().getTime());
		life.setMood(edit.getText().toString());
		if (timeDB.loadLife(new CurrentTime().getTime()).getMood() == null) {
			timeDB.saveLife(life);
		}
		else {
			timeDB.updateLife(life);
		}	
		Log.d("save picture", edit.getText().toString());
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.history_item:
			Intent intentHistory = new Intent(this, HistoryActivity.class);
			startActivity(intentHistory);
			break;
		case R.id.future_item:
			Intent intentFuture = new Intent(this, FutureActivity.class);
			startActivity(intentFuture);
			break;
		case R.id.password_item:
			Intent intentPassword = new Intent(this, SetPasswordActivity.class);
			startActivity(intentPassword);
			break;
		default:
		}
		return true;
	}
}
