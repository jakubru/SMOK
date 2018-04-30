package test.smok.logic;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Kuba on 14.04.2018.
 */

public class ReactionService extends IntentService {

    private Subsystem mSubsystem;

    public ReactionService() {
        super("ReactionService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SubsystemCreator subsystemCreator = intent.getParcelableExtra("Creator");
        mSubsystem = subsystemCreator.createSubsystem(getApplicationContext());
        while (true){
            try {
                this.mSubsystem.react();
                Thread.sleep(Configuration.getInstance(getApplicationContext()).getDatabaseCheckTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
