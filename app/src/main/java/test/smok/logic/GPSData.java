package test.smok.logic;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by Kuba on 15.03.2018.
 */

public class GPSData {
    LocationManager mLocationManager;
    public GPSData(Context context){
        this.mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public double getLongitude()
    {
        return 1;
    }
    public double getLatitude()
    {
        return 1;
    }
}
