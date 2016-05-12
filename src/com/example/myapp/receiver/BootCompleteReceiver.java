
package com.example.myapp.receiver;

import com.example.myapp.R;
import com.example.myapp.activity.PlanActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, "������¼�µ�һ���", System.currentTimeMillis());
		Intent newIntent = new Intent(context, PlanActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, newIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(context, "Plan", "Plan your life", pi);
        manager.notify(1,notification);
	}

}