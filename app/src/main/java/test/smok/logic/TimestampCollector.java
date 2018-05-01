package test.smok.logic;

/**
 * Created by Kuba on 14.04.2018.
 */

public class TimestampCollector implements DataCollector {

    private static TimestampCollector mInstance;

    private TimestampCollector(){

    }

    public static TimestampCollector getInstance(){
        if(mInstance == null){
            mInstance = new TimestampCollector();
        }
        return mInstance;
    }

    @Override
    public String collectData() {
        Long tsLong = System.currentTimeMillis()/1000;
        return "Timestamp:" + tsLong.toString() + "|";
    }
}
