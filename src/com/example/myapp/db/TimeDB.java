package com.example.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.myapp.model.Item;
import com.example.myapp.model.Life;
import com.example.myapp.model.Plan;
import com.example.myapp.model.Summary;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class TimeDB {

    public static final String DB_NAME = "time";

    public static final int VERSION = 1;

    private static TimeDB timeDB;

    private SQLiteDatabase db;

    private TimeDB(Context context) {
        TimeOpenHelper dbHelper = new TimeOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static TimeDB getInstance(Context context) {
        if (timeDB == null) {
            timeDB = new TimeDB(context);
        }
        return timeDB;
    }

    public void saveLife(Life life) {
        //添加
        if (life != null) {
            ContentValues values = new ContentValues();
            values.put("time", life.getTime());
            values.put("mood", life.getMood());
            db.insert("Life", null, values);
        }
    }

    public void saveSummary(Summary summary) {
        if (summary != null) {
            ContentValues values = new ContentValues();
            values.put("time", summary.getTime());
            values.put("conclusion", summary.getConclusion());
            db.insert("Summary", null, values);
        }
    }

    public void savePlan(Plan plan) {
        if (plan.getPlanItem().get(0).getContent() != null) {
        	//null ""
        	Log.v("timedb", plan.getPlanItem().get(0).getContent()+"");
        	if (plan.getPlanItem().get(0).getContent().equals("null"))
        		Log.v("plan.getPlanItem().get(0).getContent()","== null");
            ContentValues values = new ContentValues();
            values.put("time", plan.getTime());
            values.put("number", plan.getNumber());
            String content = saveItem(plan.getPlanItem());
            Log.v("savePlan", content);
            values.put("content", content);
            db.insert("Plan", null,values);
        }
    }
    
    public long loadTime(int id) {
		Cursor cursor = null;
		String minTime = null;//new String[] { "time" }
		cursor = db.query("Summary", null, "id = ?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				minTime = cursor.getString(cursor.getColumnIndex("time"));
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return Long.valueOf(minTime);
	}


    private String saveItem(List<Item> list) {
        String content = null;
        for(int i = 0; i<list.size(); i++) {
            Item item = list.get(i);
            if (i == 0) content = item.getContent() + "|" +item.getIsCheck();
            //remember 从零开始数!!!!!
            else content = content+ "," + item.getContent() + "|" +item.getIsCheck();
        }
        return content;
    }

    public void updateLife(Life life) {
        ContentValues values = new ContentValues();
        values.put("mood", life.getMood());
        db.update("Life", values, "time = ?", new String[] {String.valueOf(life.getTime())});
    }

    public void updateSummary(Summary summary) {
        ContentValues values = new ContentValues();
        values.put("conclusion", summary.getConclusion());
        db.update("Summary", values, "time = ?", new String[] {String.valueOf(summary.getTime())});
    }

    public void updatePlan(Plan plan) {
        ContentValues values = new ContentValues();
        String content = saveItem(plan.getPlanItem());
        values.put("content", content);
        Log.v("ok", content + "");
        values.put("number", plan.getNumber());
        db.update("Plan", values, "time = ?", new String[] {String.valueOf(plan.getTime())});
    }

    public Life loadLife(long time) {
        //查询
        Cursor cursor = db.query("Life", null, "time = ?", new String[] {String.valueOf(time)},
                null, null,null);
        Life life = new Life();
        if (cursor.moveToFirst()) {
            do {
                life.setTime(time);
                life.setMood(cursor.getString(cursor.getColumnIndex("mood")));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return life;
    }

    public Summary loadSummary(long time) {
        Cursor cursor = db.query("Summary", null, "time = ?", new String[] {String.valueOf(time)},
                null, null,null);
        Summary summary = new Summary();
        if (cursor.moveToFirst()) {
            do {
                summary.setTime(time);
                summary.setConclusion(cursor.getString(cursor.getColumnIndex("conclusion")));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return summary;
    }

    public Plan loadPlan(long time) {
        Cursor cursor = db.query("Plan", null, "time = ?", new String[] {String.valueOf(time)},
                null, null,null);
        Plan plan = Plan.getInstance();
        if (cursor.moveToFirst()) {
            do {
                plan.setTime(time);
                plan.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                loadItem(content, plan);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return plan;
    }

    private void loadItem(String content, Plan plan) {
        if (!TextUtils.isEmpty(content)) {
            String[] array = content.split(",");
            if (array != null && array.length > 0) {
                for (String a : array) {
                    String[] array2 = a.split("\\|");
                    Item item = new Item();
                    item.setContent(array2[0]);
                    if (array2[1] == "true") item.setIsCheck(true);
                    else item.setIsCheck(false);
                    plan.insertPlanItem(item);
                }
            }
        }
    }

    public void deleteLife(long time) {
        db.delete("Life", "time = ?", new String[] {String.valueOf(time)});
    }

    public void deleteSummary(long time) {
        db.delete("Summary", "time = ?", new String[] {String.valueOf(time)});
    }

    public void deletePlan(long time) {
        db.delete("Plan", "time = ?", new String[] {String.valueOf(time)});
    }
    
    public void newDB() {
    	db.execSQL("drop table if exists Plan");
    	db.execSQL("drop table if exists Summary");
    	db.execSQL("drop table if exists Life");
    	
    	String CREATE_PLAN = "create table Plan (" +
                "id integer primary key autoincrement, " +
                "time real, " +
                "number integer, " +
                "content text)";
    	String CREATE_SUMMARY = "create table Summary ("
                + "id integer primary key autoincrement, "
                + "time real, "
                + "conclusion text)";
        String CREATE_LIFE = "create table Life (" +
                "id integer primary key autoincrement, " +
                "time real, " +
                "mood text)" ;

     	  db.execSQL(CREATE_LIFE);
          db.execSQL(CREATE_PLAN);
          db.execSQL(CREATE_SUMMARY);
    	//onCreate(db);
    }
}

