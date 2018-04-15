package test.smok.logic;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kuba on 31.03.2018.
 */

public class CellDataManagerCreator extends DataManagerCreator {

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
        dataManager.addCollector(new TimestampCollector());
        return dataManager;
    }

    public static final Parcelable.Creator<CellDataManagerCreator> CREATOR
            = new Parcelable.Creator<CellDataManagerCreator>() {
        public CellDataManagerCreator createFromParcel(Parcel in) {
            return new CellDataManagerCreator(in);
        }

        public CellDataManagerCreator[] newArray(int size) {
            return new CellDataManagerCreator[size];
        }
    };

    private CellDataManagerCreator(Parcel in){

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
