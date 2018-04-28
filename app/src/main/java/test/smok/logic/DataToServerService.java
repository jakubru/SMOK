package test.smok.logic;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Kuba on 08.04.2018.
 */

public class DataToServerService extends IntentService {

    private DataManager mDataManager;

    public DataToServerService(){
        super("IntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        DataManagerCreator dataManagerCreator = intent.getParcelableExtra("Creator");
        mDataManager = dataManagerCreator.createDataManager(getApplicationContext());
        while(true){
            try{
                mDataManager.collectfromCollectors();
                mDataManager.send();
                Thread.sleep(Configuration.getInstance(getApplicationContext()).getDataSendTime());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
