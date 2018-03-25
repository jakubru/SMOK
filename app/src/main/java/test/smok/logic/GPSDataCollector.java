package test.smok.logic;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by Kuba on 24.03.2018.
 */

public class GPSDataCollector implements DataCollector {

    private LocationManager mLocationManager;
    private GPSLocationListener mGPSLocationListener;

    public GPSDataCollector(Context context){
        this.mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.mGPSLocationListener = new GPSLocationListener();
        try{
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,1,this.mGPSLocationListener);
        }
        catch(SecurityException e){

        }
    }

    @Override
    public String collectData() {
        return Double.toString(this.mGPSLocationListener.getLongitude()) + ";" + Double.toString(this.mGPSLocationListener.getLatitude()) + ";" + Float.toString(this.mGPSLocationListener.getAccuracy()) + "|";
    }

}
