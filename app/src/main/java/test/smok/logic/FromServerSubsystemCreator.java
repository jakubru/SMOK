package test.smok.logic;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kuba on 15.04.2018.
 */

public class FromServerSubsystemCreator extends SubsystemCreator {
    @Override
    public Subsystem createSubsystem(Context context) {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static final Parcelable.Creator<FromServerSubsystemCreator> CREATOR
            = new Parcelable.Creator<FromServerSubsystemCreator>() {
        public FromServerSubsystemCreator createFromParcel(Parcel in) {
            return new FromServerSubsystemCreator(in);
        }

        public FromServerSubsystemCreator[] newArray(int size) {
            return new FromServerSubsystemCreator[size];
        }
    };

    private FromServerSubsystemCreator(Parcel in){

    }
}
