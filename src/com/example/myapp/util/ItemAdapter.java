package com.example.myapp.util;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
public class ItemAdapter extends ArrayAdapter<Item>  {
    //优化?
    private int resourceId;

    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position ,View convertView, ViewGroup parent) {
    	final int mPosition;
    	mPosition = position;
        Item item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
        	view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        	viewHolder = new ViewHolder();
        	viewHolder.check = (CheckBox) view.findViewById(R.id.check);
        	viewHolder.itemContent = (TextView) view.findViewById(R.id.item_content);
        	view.setTag(viewHolder);
        } else {
        	view = convertView;
        	viewHolder = (ViewHolder) view.getTag();
        }
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        //getContext()?
        viewHolder.check.setOnCheckedChangeListener( new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    		// TODO Auto-generated method stub
    		    Plan plan = Plan.getInstance();
    		  if (isChecked){
    			  Log.v("hello",buttonView.getId()+"");
    		      plan.getPlanItem().get(mPosition).setIsCheck(true);
    		      Log.v("hello","setCheck");
    		  } else {
    		      plan.getPlanItem().get(mPosition).setIsCheck(false);
    		  }
            }
    	});
        viewHolder.check.setChecked(item.getIsCheck());
        Log.v("setChecked",item.getIsCheck()+""+position);
        viewHolder.itemContent.setText(item.getContent());
        if (item.getContent() == null) {
        	viewHolder.check.setVisibility(View.GONE);
        	viewHolder.itemContent.setVisibility(View.GONE);
        } else {
        	viewHolder.check.setVisibility(View.VISIBLE);
        	viewHolder.itemContent.setVisibility(View.VISIBLE);
        }
        return view;
    }
    
    class ViewHolder {
        CheckBox check;
        TextView itemContent;
    }
}



//package com.example.myapp.util;
//
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.TextView;
//
//import com.example.myapp.R;
//import com.example.myapp.model.Item;
//import com.example.myapp.model.Plan;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2016/4/30.
// */
//public class ItemAdapter extends ArrayAdapter<Item>  {
//    //优化?
//    private int resourceId;
//    private CheckBox check;
//    private TextView itemContent;
//
//    public ItemAdapter(Context context, int textViewResourceId, List<Item> objects) {
//        super(context, textViewResourceId, objects);
//        resourceId = textViewResourceId;
//    }
//
//    @Override
//    public View getView(int position ,View convertView, ViewGroup parent) {
//    	final int mPosition;
//    	mPosition = position;
//        Item item = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
//        //getContext()?
//        check = (CheckBox) view.findViewById(R.id.check);
//        itemContent = (TextView) view.findViewById(R.id.item_content);
//        check.setChecked(item.getIsCheck());
//        check.setOnCheckedChangeListener( new OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//    		// TODO Auto-generated method stub
//    		    Plan plan = Plan.getInstance();
//    		  if (isChecked){
//    			  Log.v("hello",buttonView.getId()+"");
//    			  plan.getPlanItem().get(mPosition).setIsCheck(true);
//    		      Log.v("hello","setCheck");
//    		  } else {
//    		      plan.getPlanItem().get(mPosition).setIsCheck(false);
//    		  }
//            }
//    	});
//        Log.v("setChecked",item.getIsCheck()+""+position);
//        itemContent.setText(item.getContent());
//        return view;
//    }
//}



