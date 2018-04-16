package test.smok.logic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import test.smok.R;

/**
 * Created by Kuba on 16.04.2018.
 */

public class ReactionSubsystem extends Subsystem {

    private DataCollector mDataCollector;

    public ReactionSubsystem(Context context, DataCollector dataCollector) {
        super(context);
        this.mDataCollector = dataCollector;
    }

    @Override
    public void react() {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this.mContext)
                        .setSmallIcon(R.drawable.mylogo)
                        .setContentTitle("My Notification Title")
                        .setContentText("Something interesting happened");
        int NOTIFICATION_ID = 12345;

        Intent targetIntent = new Intent(this.mContext, ReactionService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.mContext, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
    }
}
