package test.smok.logic;

/**
 * Created by Kuba on 16.12.2017.
 */

public abstract class Parser {

    public Parser(){};

    abstract public void parse(String Data,String delimiter,String path);
}
