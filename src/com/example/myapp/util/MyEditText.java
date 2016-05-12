package com.example.myapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {
	
	public MyEditText(Context context) {
		super(context);
	}
	public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	@SuppressWarnings("deprecation")
	public void displayBitmapOnText(Bitmap bitmap, MyEditText edit,String imagePath) {
		SpannableString mSpan1 = new SpannableString("|" + "img" + imagePath + "|");
		if(bitmap == null) return;
        int start = edit.getSelectionStart();
		mSpan1.setSpan(new ImageSpan(bitmap) , 0, mSpan1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		 //        mSpan1.toString();
		if(edit != null) {
			Editable et = edit.getText();
		    et.insert(start, mSpan1);
		    edit.setText(et);
		    edit.setSelection(start + mSpan1.length());
		 }
} 

		 //edit.setLineSpacing(10f, 1f);
	//	try {
//		TextProperty tp = new TextProperty(WORDNUM, new InputStreamReader(getResources().getAssets().open("1.txt")));
//		Bitmap bitmap = Bitmap.createBitmap(WIDTH, 20*tp.getHeigt(), Config.ARGB_8888);
//		Canvas canvas = new Canvas(bitmap);
//		Paint paint = new Paint();
//		String [] ss = tp.getContext();
//			for(int i=0;i<tp.getHeigt();i++){
//			canvas.drawText(ss[i], x, y, paint);
//			y=y+20;
//			}
//		
//		canvas.save(Canvas.ALL_SAVE_FLAG);
//		canvas.restore();
//		String path = Environment.getExternalStorageDirectory() + "/image.png";
//	    System.out.println(path);
//			FileOutputStream os = new FileOutputStream(new File(path));
//			bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
//			os.flush();
//			os.close();
//		}
//		   catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//	public void insertDrawable(int id) {
//		final SpannableString ss = new SpannableString("easy");
//		//得到drawable对象，即所要插入的图片
//		Drawable d = getResources().getDrawable(id);
//		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//		//用这个drawable对象代替字符串easy
//		ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//		//包括0但是不包括"easy".length()即：4。[0,4)。值得注意的是当我们复制这个图片的时候，实际是复制了"easy"这个字符串。
//		ss.setSpan(span, 0, "easy".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//		append(ss);
//	}
}				