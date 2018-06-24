package test.smok.logic;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.smok.MainActivity;
import test.smok.database.AppDatabase;
import test.smok.enity.GMS;
import test.smok.utils.DatabaseFacade;

/**
 * Created by matthew on 23.06.18.
 */

public class UpdateDatabase {
    protected TelephonyManager mTelephonyManager;


    public void updateDatabase() {
        //==================
        System.out.println("UpdateDatabase");
        this.mTelephonyManager = (TelephonyManager) MainActivity.context.getSystemService(Context.TELEPHONY_SERVICE);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(new Runnable() {
            public void run() {
                try {

                    URL url = new URL("https://smok-prot-5.herokuapp.com/Configuration?want=db&IMSI=260021711614296");
                    System.out.println(url);
                    ReadFromXMLData readFromXMLData = new ReadFromXMLData();
                    CreateDataToDatabase createDataToDatabase = new CreateDataToDatabase();
                    AppDatabase appDatabase = AppDatabase.getAppDatabase(MainActivity.context);
                    DatabaseFacade databaseFacade = new DatabaseFacade();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        List<GMS> list = createDataToDatabase.addGMSToDatabase(readFromXMLData.GetNodeList("gsmData", url));
                        for (GMS g : list
                                ) {
                            System.out.println(g);
                            databaseFacade.deleteGms(appDatabase,g);
                            databaseFacade.addGms(appDatabase, g);
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.shutdown();
        //==================
    }

    private String getIMSI() {
        try {
            return this.mTelephonyManager.getSubscriberId();
        } catch (SecurityException e) {
            return "";
        }
    }

}
