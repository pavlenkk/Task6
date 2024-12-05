package com.example.task6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;
import android.app.PendingIntent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("TITLE");
        String message = intent.getStringExtra("MESSAGE");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "REMINDER_CHANNEL")
                // .setSmallIcon(R.drawable.ic_reminder) // Замените на ваш логотип
                .setContentTitle(title)
                .setContentText(message)

                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("MESSAGE", message);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        notificationManager.notify(1, builder.build());
    }

}
