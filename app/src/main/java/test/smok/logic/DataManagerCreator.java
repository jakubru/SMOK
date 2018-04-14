package test.smok.logic;

import android.content.Context;
import android.os.Parcelable;

/**
 * Created by Kuba on 31.03.2018.
 */

public abstract class DataManagerCreator implements Parcelable{
    public abstract DataManager createDataManager(Context context);
}
