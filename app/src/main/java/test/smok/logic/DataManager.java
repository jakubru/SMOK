package test.smok.logic;

/**
 * Created by Kuba on 03.03.2018.
 */

public class DataManager {

    Parser mParser;
    DataCollector mDataCollector;

    public DataManager(Parser parser, DataCollector DataCollector){
        this.mParser = parser;
        this.mDataCollector = DataCollector;
    }

    public String collectfromCollectors(){
        return this.mDataCollector.collectData();
    }


}
