package com.example.myapp.util;

import java.util.Calendar;

import android.util.Log;

/**
 * Created by Administrator on 2016/5/1.
 */
public class CurrentTime {

    private Calendar ca = Calendar.getInstance();
    private int year = ca.get(Calendar.YEAR) % 100;//��ȡ���
    private int month=ca.get(Calendar.MONTH) + 1;//��ȡ�·�
    private int day=ca.get(Calendar.DATE);//��ȡ��
    private long time = 10000*year + 100*month +day;

    public long getTime() {
        
        Log.v("time:", time + "");
        return time;
    }
}
