package test.smok.logic;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Kuba on 28.04.2018.
 */

public class Configuration {

    private static Configuration mInstance;
    private String mServerIP;
    private LinkedList<String> mAdditionalServers;
    private int mDataSendTime;
    private int mDatabaseCheckTime;
    private Context mContext;
    private float mLongitude;
    private float mLatitude;
    private float mRadius;

    public static Configuration getInstance(Context context){
        if (mInstance == null){
            try{
                mInstance = new Configuration(context);
            }
            catch(FileNotFoundException f){
                f.printStackTrace();
            }
        }
        return mInstance;
    }

    public void writeConfigToFile(String data){
        /*TODO tu do pliku potrzeba zapisywać dane w formacie XML, przychodzące z serwera oraz uaktualniać wszystkie pola prywatne po dodaniu pliku*/
    }

    private Configuration(Context context) throws FileNotFoundException {
        this.mContext = context;
        File file = new File(mContext.getFilesDir().getPath() + "/config.txt");
        Scanner in = new Scanner(file);
        this.mDataSendTime = Integer.parseInt(in.nextLine());
        this.mDatabaseCheckTime = Integer.parseInt(in.nextLine());
        /*TODO wersja nie xml, tylko po to ,zeby sprawdzić czy dziala, domyślnie ma byc pobieranie z formatu XML tak jak metodę wyżej */
    }

    public int getDatabaseCheckTime(){
        return this.mDatabaseCheckTime;
    }

    public LinkedList<String> getListOfAdditionalServers(){
        return mAdditionalServers;
    }

    public String getServerIP(){
        return mServerIP;
    }

    public int getDataSendTime(){
        return mDataSendTime;
    }

    public float getLongitude(){
        return this.mLongitude;
    }

    public float getLatitude(){
        return this.mLatitude;
    }

    public float getRadius(){
        return this.mRadius;
    }
}
