package test.smok.logic;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

/**
 * Created by Kuba on 08.04.2018.
 */


@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
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
