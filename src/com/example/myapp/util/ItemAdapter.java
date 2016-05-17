package com.example.myapp.util;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.model.Item;
import com.example.myapp.model.Plan;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    //�Ż�?
    private int resourceId;
    private ViewHolder viewHolder;
    Plan plan = Plan.getInstance();
    private static boolean appearButton = false;
    private View view;
    private Context context;
    private float startX;

    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position ,View convertView, ViewGroup parent) {
    	final int mPosition = position;
    	final ViewHolder mViewHolder;
    	//final View fView;
        Item item = getItem(position);
        if (convertView == null) {
        	view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        	viewHolder = new ViewHolder();
        	viewHolder.check = (CheckBox) view.findViewById(R.id.check);
        	viewHolder.itemContent = (TextView) view.findViewById(R.id.item_content);
        	viewHolder.delete = (Button) view.findViewById(R.id.delete_button);
        	view.setTag(viewHolder);
        } else {
        	view = convertView;
        	viewHolder = (ViewHolder) view.getTag();
        }
        
        

//        DisplayMetrics metric = new DisplayMetrics();
//        ItemAdapter.this.getWindowManager().getDefaultDisplay().getMetrics(metric);  
//        int width = metric.widthPixels;     // ��Ļ��ȣ����أ�  
//        int height = metric.heightPixels;   // ��Ļ�߶ȣ����أ�  
//        return height;

        viewHolder.itemContent.setHeight(getScreenHeight(context));
        //fView = view;
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        //getContext()?
        mViewHolder = viewHolder;
        viewHolder.check.setOnClickListener( new OnClickListener(){
            @Override
            public void onClick(View v) {
    		// TODO Auto-generated method stub
    		  if (!plan.getPlanItem().get(mPosition).getIsCheck()){
    		      plan.getPlanItem().get(mPosition).setIsCheck(true);
    		      Item item = plan.getPlanItem().get(mPosition);
    		      Log.d("hello", mPosition +""+ item.getContent());
    		      Log.d("hello","ɾ��dλ�ã�"+mPosition);
    		      Log.d("hello", "ɾ��֮ǰ�ĳ��ȣ�"+plan.getPlanItem().size());
    		      plan.getPlanItem().remove(mPosition);
    		      Log.d("hello", "ɾ��֮��ĳ��ȣ�"+plan.getPlanItem().size());
    		      plan.getPlanItem().add(plan.getPlanItem().size(), item);
    		      Log.d("hello", "���֮��ĳ��ȣ�"+plan.getPlanItem().size());
    		      notifyDataSetChanged();
    		  } else {
    		      plan.getPlanItem().get(mPosition).setIsCheck(false);
    		      Item item = plan.getPlanItem().get(mPosition);
    		      plan.getPlanItem().remove(mPosition);
    		      plan.getPlanItem().add(0, item);
    		      notifyDataSetChanged();
    		  }
            }
    	});

        
//view viewHolder difference
        mViewHolder.itemContent.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//View v ����һ���ģ��ı����Զ���ǵ�һ���������һ��������ֻ����inner class
		    	
		        float endX;
		        //static float startX;
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					startX = event.getX();
					Log.v("action_down", startX + "");
		        //�ж�֮ǰ�Ƿ������ɾ����ť��������ھ�����
					if(appearButton != false) {
						appearButton = false;   
						//appearButton�����Ǿֲ�������notifyDataSetChanged()��appearButton��Ȼ��true				
						Log.v("appear?", "yes");
						notifyDataSetChanged();
//						viewHolder = (ViewHolder) view.getTag();
//						viewHolder.delete.setVisibility(View.GONE);
					}
				} else if(event.getAction() == MotionEvent.ACTION_UP) {
					endX = event.getX();
					if(appearButton == false) {
		        //���º��ɿ��ľ���ֵ�����20ʱ����ʾɾ����ť��������ʾ
						Log.v("appear button1", appearButton+"");
						if(startX - endX > 20) {
							Log.v("action_up", startX + "");
//							Math.abs(startX - endX) > 20
							Log.v("start-end > 20","yes");
//							viewHolder = (ViewHolder) view.getTag();
//							viewHolder.delete.setVisibility(View.VISIBLE);
							mViewHolder.delete.setVisibility(View.VISIBLE);
							appearButton = true;
							Log.v("appear button2", appearButton+"");
						} 
					}
				} 
				return true;
			}        		
        });
        //Ϊɾ����ť��Ӽ����¼�
//        viewHolder.delete.setOnClickListener (new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//if(holder.delete != null) {
//				Log.v("appear button!", appearButton+"");
//				viewHolder.delete.setVisibility(View.GONE);
//				appearButton = false;
//				Log.v("appear button?", appearButton+"");
//				plan.deletePlanItem(mPosition);
//				//arrays.remove(position);
//				notifyDataSetChanged();
//			}
//        });

        viewHolder.delete.setOnClickListener (new OnClickListener() {
        	@Override
        	//������Ҫposition��ֻ����inner class
        	//positionҪ��inner class��ʹ�ã�����final
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		switch(v.getId()) {
        		case R.id.delete_button:
//        			Log.v("appear button!", appearButton+"");
//        			mViewHolder.delete.setVisibility(View.GONE);
         			appearButton = false;
//        			Log.v("appear button?", appearButton+"");
        			plan.deletePlanItem(mPosition);
        			//arrays.remove(position);.
        			notifyDataSetChanged();
        			break;
        		}
        	}
        });

        viewHolder.check.setChecked(item.getIsCheck());
        Log.v("setChecked",item.getIsCheck()+""+position);
        viewHolder.itemContent.setText(item.getContent());
        if (item.getIsCheck()) 
        	viewHolder.itemContent.setTextColor(viewHolder.itemContent.getResources().getColor(R.color.dark_gray));
        else viewHolder.itemContent.setTextColor(viewHolder.itemContent.getResources().getColor(R.color.black));
        if (item.getContent() == null) {
        	viewHolder.check.setVisibility(View.GONE);
        	viewHolder.itemContent.setVisibility(View.GONE);
        	viewHolder.delete.setVisibility(View.GONE);
        } else {
        	viewHolder.check.setVisibility(View.VISIBLE);
        	viewHolder.itemContent.setVisibility(View.VISIBLE);
        	viewHolder.delete.setVisibility(View.GONE);
        }
        return view;
    }
    
    public static int getScreenHeight(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display.getHeight()/15;
    }
    
    class ViewHolder {
        CheckBox check;
        TextView itemContent;
        Button delete;
    }
}
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()) {
//		case R.id.delete_button:
//			Log.v("appear button!", appearButton+"");
//			viewHolder.delete.setVisibility(View.GONE);
//			appearButton = false;
//			Log.v("appear button?", appearButton+"");
//			plan.deletePlanItem(mPosition);
//			//arrays.remove(position);.
//			notifyDataSetChanged();
//			break;
//		}
//	}

//	@Override
//	public boolean onTouch(View v, MotionEvent event) {
//        float startX = 0;
//        float endX;
//		if(event.getAction() == MotionEvent.ACTION_DOWN) {
//			startX = event.getX();
//        //�ж�֮ǰ�Ƿ������ɾ����ť��������ھ�����
//			if(appearButton != false) {
//				appearButton = false;
//				mViewHolder.delete.setVisibility(View.GONE);
//			}
//		} else if(event.getAction() == MotionEvent.ACTION_UP) {
//			endX = event.getX();
//			if(appearButton == false) {
//        //���º��ɿ��ľ���ֵ�����20ʱ����ʾɾ����ť��������ʾ
//				Log.v("appear button1", appearButton+"");
//				if(Math.abs(startX - endX) > 20) {
//					mViewHolder.delete.setVisibility(View.VISIBLE);
//					appearButton = true;
//					Log.v("appear button2", appearButton+"");
//					}
//				}
//			} 
//		    return true;
//	}




