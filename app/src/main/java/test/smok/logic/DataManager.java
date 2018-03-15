package test.smok.logic;

/**
 * Created by Kuba on 03.03.2018.
 */

public class DataManager {

    Parser mParser;
    DataCollector mDataCollector;

    public DataManager(Parser parser, DataCollector dataCollector){
        this.mParser = parser;
        this.mDataCollector = dataCollector;
    }


    public void sendToServer(){
        //prawdopodobnie powinna zostać stworzona klasa, która obsługuje wysyłanie na serwer i przekazana tej metodzie w parametrze

    }
}
