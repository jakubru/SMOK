package test.smok.logic;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Kuba on 22.03.2018.
 */

public class GPSLocationListener implements LocationListener {

    private double latitude;
    private double longitude;
    private float accuracy;

    @Override
    public void onLocationChanged(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.accuracy = location.getAccuracy();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public float getAccuracy(){
        return this.accuracy;
    }
}
