package com.example.myapp.util;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/5/1.
 */
public class CurrentTime {

    private static Calendar ca = Calendar.getInstance();
    private static int year = ca.get(Calendar.YEAR);//��ȡ���
    private static int month=ca.get(Calendar.MONTH);//��ȡ�·�
    private static int day=ca.get(Calendar.DATE);//��ȡ��
    private static double time = 10000*year + 100*month +day;

    public static double getTime() {
        return time;
    }
}
