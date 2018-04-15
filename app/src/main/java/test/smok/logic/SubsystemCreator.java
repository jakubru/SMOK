package test.smok.logic;

import android.content.Context;
import android.os.Parcelable;

/**
 * Created by Kuba on 15.04.2018.
 */

public abstract class SubsystemCreator implements Parcelable {
    public abstract Subsystem createSubsystem(Context context);
}
