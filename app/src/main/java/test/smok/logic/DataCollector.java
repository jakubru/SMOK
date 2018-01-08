package test.smok.logic;

import android.content.Context;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Kuba on 16.12.2017.
 */

public interface DataCollector extends ActivityCompat.OnRequestPermissionsResultCallback {
    String [] collect(Context context);
}
