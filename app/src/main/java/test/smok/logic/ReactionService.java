package test.smok.logic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Kuba on 14.04.2018.
 */

public class ReactionService extends Service {

    private Subsystem mSubsystem;

    @Override
    public int onStartCommand(Intent intent, int flags, int StartId){
        SubsystemCreator subsystemCreator = intent.getParcelableExtra("Creator");
        mSubsystem = subsystemCreator.createSubsystem(getApplicationContext());
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}