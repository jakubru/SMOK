package test.smok.logic;

/**
 * Created by Kuba on 03.03.2018.
 */

public class DataManager {

    NetworkTypeState mNetworkTypeState;
    Parser mParser;

    public DataManager(Parser parser){
        this.mParser = parser;
    }

    private void requestCollect(){
        mNetworkTypeState.handleCollect();
    }

}
