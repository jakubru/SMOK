package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public abstract class Parser {
    private DataCollector dt;
    abstract public void parse(char delimiter1, char delimiter2);
}
