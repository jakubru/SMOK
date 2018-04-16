package test.smok.logic;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kuba on 16.04.2018.
 */

public class ReactionSubsystemCreator extends SubsystemCreator {

    public static final Parcelable.Creator<ReactionSubsystemCreator> CREATOR
            = new Parcelable.Creator<ReactionSubsystemCreator>() {
        public ReactionSubsystemCreator createFromParcel(Parcel in) {
            return new ReactionSubsystemCreator(in);
        }

        public ReactionSubsystemCreator[] newArray(int size) {
            return new ReactionSubsystemCreator[size];
        }
    };

    private ReactionSubsystemCreator(Parcel in){

    }
    public ReactionSubsystemCreator(){

    }

    @Override
    public Subsystem createSubsystem(Context context) {
        GSMCellDataCollector g = new GSMCellDataCollector(context);
        LTECellDataCollector l = new LTECellDataCollector(context);
        CDMACellDataCollector c = new CDMACellDataCollector(context);
        WCDMACellDataCollector w = new WCDMACellDataCollector(context);
        g.setNextCollector(l);
        l.setNextCollector(c);
        c.setNextCollector(w);
        return new ReactionSubsystem(context,g);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
