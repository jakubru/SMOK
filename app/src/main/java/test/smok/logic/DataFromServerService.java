package test.smok.logic;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kuba on 15.04.2018.
 */

public class DataFromServerService extends IntentService {


    Subsystem mSubsystem;

    public DataFromServerService(){
        super("DataFromServerService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SubsystemCreator subsystemCreator = intent.getParcelableExtra("Creator");
        mSubsystem = subsystemCreator.createSubsystem(getApplicationContext());
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getDataFromServer(String url) {
        String inputLine="";
        String output="";
        try {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        while ((inputLine = in.readLine()) != null) {
            System.out.println("URL " + inputLine);
            output+=inputLine;
        }
        in.close();
            System.out.println("output "+output);
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
    public void updateDb(){
        UpdateDatabase updateDatabase=new UpdateDatabase();
        updateDatabase.updateDatabase();
    }
}
