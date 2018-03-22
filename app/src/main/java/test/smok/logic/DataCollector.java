package test.smok.logic;

import android.content.Context;
import android.location.LocationManager;
import android.telephony.TelephonyManager;

/**
 * Created by Kuba on 16.12.2017.
 */
public abstract class DataCollector {
    protected TelephonyManager mTelephonyManager;
    protected LocationManager mLocationManager;
    public abstract String [] collect();
    public DataCollector(Context context){
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try{
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,20,30,new GPSLocationListener());
        }
        catch(SecurityException e){

        }
    }

    public String getLocalization(){
        try{
            GPSLocationListener GPSLocationListener = new GPSLocationListener();
            return Double.toString(GPSLocationListener.getLatitude()) + ";" + Double.toString(GPSLocationListener.getLongitude())+ ";" + Float.toString(GPSLocationListener.getAccuracy());
        }
        catch(SecurityException e){
            return "";
        }
    }


    public String getImsi(){
        try{
            return this.mTelephonyManager.getSubscriberId();
        }
        catch (SecurityException e){
            return "";
        }
    }

}
