package test.smok.logic;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kuba on 15.04.2018.
 */

public class CellInfoSubsystemCreator extends SubsystemCreator {

    @Override
    public Subsystem createSubsystem(Context context) {
        GSMCellDataCollector g = new GSMCellDataCollector(context);
        LTECellDataCollector l = new LTECellDataCollector(context);
        CDMACellDataCollector c = new CDMACellDataCollector(context);
        WCDMACellDataCollector w = new WCDMACellDataCollector(context);
        g.setNextCollector(l);
        l.setNextCollector(c);
        c.setNextCollector(w);
        return new CellInfoSubsystem(g,context);
    }


    public static final Parcelable.Creator<CellInfoSubsystemCreator> CREATOR
            = new Parcelable.Creator<CellInfoSubsystemCreator>() {
        public CellInfoSubsystemCreator createFromParcel(Parcel in) {
            return new CellInfoSubsystemCreator(in);
        }

        public CellInfoSubsystemCreator[] newArray(int size) {
            return new CellInfoSubsystemCreator[size];
        }
    };

    private CellInfoSubsystemCreator(Parcel in){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
