package test.smok.logic;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Kuba on 08.04.2018.
 */

public class MyIntentSevice extends IntentService {

    private DataManager mDataManager;
    private DataManagerCreator mDataManagerCreator;

    public MyIntentSevice(){
        super("IntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mDataManagerCreator = intent.getParcelableExtra("Creator");
        mDataManager = mDataManagerCreator.createDataManager(getApplicationContext());
        while(true){
            try{
                mDataManager.collectfromCollectors();
                mDataManager.send();
                Thread.sleep(5000);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
