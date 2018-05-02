package test.smok.logic;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import test.smok.R;
import test.smok.database.AppDatabase;
import test.smok.utils.DatabaseFacade;
import test.smok.utils.DatabaseMake;

/**
 * Created by Kuba on 16.04.2018.
 */

public class ReactionSubsystem extends Subsystem {

    private DataCollector mDataCollector;
    private DatabaseMake mDatabaseMake;
    private GPSDataCollector mGPSDataCollector;
    private boolean earlierState;

    public ReactionSubsystem(Context context, DataCollector dataCollector) {
        super(context);
        this.mDataCollector = dataCollector;
        this.mDatabaseMake = new DatabaseMake(new DatabaseFacade());
        this.mGPSDataCollector = GPSDataCollector.getInstance(context);
        this.earlierState = true;
    }

    @Override
    public void react() {
        CellDataCollector cellDataCollector = (CellDataCollector) mDataCollector;
        String [] registered = cellDataCollector.getRegisteredCellInfo();
        boolean flag = true;
        Configuration conf = Configuration.getInstance(mContext);
        double radius = Functions.checkArea(conf.getLatitude(),conf.getLongitude(),mGPSDataCollector.getLat(), mGPSDataCollector.getLong());
        if (radius < conf.getRadius()) {
            if (registered[0] == "GSM") {
                flag = mDatabaseMake.databaseFacade.checkIfExistsGSM(AppDatabase.getAppDatabase(this.mContext), registered[1], registered[2], registered[3], registered[5]);
            }
            else if (registered[0] == "CDMA") {
                flag = mDatabaseMake.databaseFacade.checkIfExistsCDMA(AppDatabase.getAppDatabase(this.mContext), registered[1], registered[2], registered[3], registered[4]);
            }
            else if (registered[0] == "WCDMA") {
                flag = mDatabaseMake.databaseFacade.checkIfExistsWCDMA(AppDatabase.getAppDatabase(this.mContext), registered[1], registered[2], registered[3], registered[4]);
            }
            else if (registered[0] == "LTE") {
                flag = mDatabaseMake.databaseFacade.checkIfExistsLTE(AppDatabase.getAppDatabase(this.mContext), registered[1], registered[3], registered[4], registered[5]);
            }
        }
        if(flag && !earlierState){
            sendOKNotification();
        }
        if(!flag && earlierState){
            sendAlertNotification();
            /*TODO przydałaby się też informacja na serwer*/
        }
        earlierState = flag;
    }

    private void sendAlertNotification(){
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this.mContext)
                        .setSmallIcon(R.drawable.mylogo)
                        .setContentTitle("Niedobrze")
                        .setContentText("Niezarejestrowana stacja");
        int NOTIFICATION_ID = 12345;

        Intent targetIntent = new Intent(this.mContext, ReactionService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.mContext, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void sendOKNotification(){
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this.mContext)
                        .setSmallIcon(R.drawable.mylogo)
                        .setContentTitle("Dobrze")
                        .setContentText("Zarejestrowana stacja");
        int NOTIFICATION_ID = 12346;

        Intent targetIntent = new Intent(this.mContext, ReactionService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.mContext, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
    }
}
