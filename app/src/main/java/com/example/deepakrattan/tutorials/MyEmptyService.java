package com.example.deepakrattan.tutorials;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

public class MyEmptyService extends Service {
    private static final String TAG = "MyEmptyService";

    NotificationManager manager;
    Notification notification;

    public MyEmptyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        displayNotiFication();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroy();
        manager.cancel(12345);
    }


    public void displayNotiFication() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
// Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(this, MainActivityOne.class);
// Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this);
        builder1.setTicker("I M Notification");
        builder1.setWhen(System.currentTimeMillis());
        builder1.setVisibility(View.VISIBLE);
        builder1.setContentTitle("Title");
        builder1.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(notifyPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(12345,builder1.build());

    }


}
