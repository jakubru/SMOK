package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public abstract class Parser {
    protected DataCollector mDataCollector;

    public Parser(){};
    public Parser (DataCollector dataCollector){
        this.mDataCollector = dataCollector;
    }

    abstract public void parse(char delimiter1, char delimiter2);
}
