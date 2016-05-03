package com.example.myapp.model;


import android.util.Log;

import com.example.myapp.util.CurrentTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class Plan {

    private double time;

    private int number;

    private List<Item> planItem = new ArrayList<Item>();
    
    private static Plan plan;

    private Plan() {
        time = CurrentTime.getTime();
        number = 1;
        Item item = new Item();
        planItem.add(item);
    }
    
    public synchronized static Plan getInstance() {
    	if (plan == null) {
    		plan = new Plan();
    	}
    	return plan;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void insertPlanItem(String content) {
    	Log.v("ok","insertPlanItem");
    	Item item =new Item();
        item.setIsCheck(false);
        item.setContent(content);
    	if (planItem.get(0).getContent() != null) {
    		Log.v("ok", planItem.get(0).getContent()+"liang");
            planItem.add(item);
            number++;
    	} else {
    		planItem.clear();
    		planItem.add(item);
    	}
    }

    public void insertPlanItem(Item item) {
    	if (planItem.get(0).getContent() != null) {
    		Log.v("ok", planItem.get(0).getContent()+"liang");
            planItem.add(item);
            number++;
    	} else {
    		planItem.clear();
    		planItem.add(item);
    	}
    }

    public void deletePlanItem(Item item) {
        planItem.remove(item);
        number--;
    }

    public List<Item> getPlanItem() {
        return planItem;
    }
}
