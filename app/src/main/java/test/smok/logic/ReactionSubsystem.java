package test.smok.logic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import test.smok.R;
import test.smok.utils.DatabaseFacade;
import test.smok.utils.DatabaseMake;

/**
 * Created by Kuba on 16.04.2018.
 */

public class ReactionSubsystem extends Subsystem {

    private DataCollector mDataCollector;
    private DatabaseMake mDatabaseMake;
    private GPSLocationListener  mGPSLocationListener;

    public ReactionSubsystem(Context context, DataCollector dataCollector) {
        super(context);
        this.mDataCollector = dataCollector;
        this.mDatabaseMake = new DatabaseMake(new DatabaseFacade());
        this.mGPSLocationListener = new GPSLocationListener();
    }

    @Override
    public void react() {
        CellDataCollector cellDataCollector = (CellDataCollector) mDataCollector;
        String registered = cellDataCollector.getRegistered();

    }

    private void sendNotification(){
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
