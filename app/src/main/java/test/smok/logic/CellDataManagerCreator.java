package test.smok.logic;

import android.content.Context;

/**
 * Created by Kuba on 31.03.2018.
 */

public class CellDataManagerCreator implements DataManagerCreator {

    @Override
    public DataManager createDataManager(Context context) {
        DataCollector gps = new GPSDataCollector(context);
        GSMCellDataCollector g = new GSMCellDataCollector(context);
        LTECellDataCollector l = new LTECellDataCollector(context);
        CDMACellDataCollector c = new CDMACellDataCollector(context);
        WCDMACellDataCollector w = new WCDMACellDataCollector(context);
        g.setNextCollector(l);
        l.setNextCollector(c);
        c.setNextCollector(w);
        DataManager dataManager = new DataManager(new XMLDataParser(), context);
        dataManager.addCollector(gps);
        dataManager.addCollector(g);
        return dataManager;
    }
}
