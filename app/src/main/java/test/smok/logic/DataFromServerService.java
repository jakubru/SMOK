package test.smok.logic;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Kuba on 15.04.2018.
 */

public class DataFromServerService extends IntentService {

    /* TODO W tym serwisie aplikacja odbiera dane z serwera */

    Subsystem mSubsystem;

    public DataFromServerService(){
        super("DataFromServerService");
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SubsystemCreator subsystemCreator = intent.getParcelableExtra("Creator");
        mSubsystem = subsystemCreator.createSubsystem(getApplicationContext());
    }
}
