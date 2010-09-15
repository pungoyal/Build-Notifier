package com.thoughtworks.buildnotifier.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.thoughtworks.buildnotifier.R;
import com.thoughtworks.buildnotifier.activities.BuildNotifier;
import com.thoughtworks.buildnotifier.web.BuildClient;

import java.util.Timer;
import java.util.TimerTask;

public class BuildMonitorService extends Service {
    Timer timer = new Timer();
    private final int POLL_INTERVAL = 10000;
    private BuildClient client;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        client = new BuildClient(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                boolean failing = client.isFailing();
                if (failing) showNotification();
                else cancelNotification();
            }
        }, 0, POLL_INTERVAL);
    }

    private void cancelNotification() {
        notificationManager.cancelAll();
    }

    private void showNotification() {
        CharSequence text = "Build failed!";
        Notification notification = new Notification(R.drawable.pool_icon, text, System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, BuildNotifier.class), 0);
        notification.setLatestEventInfo(this, "Build", text, contentIntent);
        notificationManager.notify(R.string.app_name, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        cancelNotification();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}