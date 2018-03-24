package test.smok.logic;

/**
 * Created by Kuba on 03.03.2018.
 */

public class DataManager implements Runnable {

    Parser mParser;
    DataCollector mDataCollector;

    public DataManager(Parser parser, DataCollector DataCollector){
        this.mParser = parser;
        this.mDataCollector = DataCollector;
    }


    public void sendToServer(){
        //prawdopodobnie powinna zostać stworzona klasa, która obsługuje wysyłanie na serwer i przekazana tej metodzie w parametrze
    }

    @Override
    public void run() {

    }
}
