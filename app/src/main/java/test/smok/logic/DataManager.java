package test.smok.logic;

import android.content.Context;

import java.util.LinkedList;

/**
 * Created by Kuba on 03.03.2018.
 */

public class DataManager implements Runnable {

    Parser mParser;
    LinkedList<DataCollector> mDataCollectors;
    Context mContext;
    int i;

    public DataManager(Parser parser, Context context){
        this.mParser = parser;
        this.mDataCollectors = new LinkedList<>();
        this.mContext = context;
        i = 0;
    }

    private void collectfromCollectors(){
        String data = "";
        for (DataCollector dataCollector:mDataCollectors) {
            data +=  dataCollector.collectData();
        }
        String Delimiter=":;|";
        mParser.parse(data,Delimiter, mContext.getFilesDir().getPath() + "/NetworkData" + Integer.toString(this.i++) +".xml");
    }

    public void addCollector(DataCollector dataCollector){
        this.mDataCollectors.add(dataCollector);
    }

    @Override
    public void run() {
        while (true){
            try{
                this.collectfromCollectors();
                Thread.sleep(5000);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void send(){
        //TODO wysyłanie pliku na serwer
    }
}
