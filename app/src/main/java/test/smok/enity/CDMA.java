package test.smok.enity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew on 21.04.18.
 */
@Entity(tableName = "cdma")
public class CDMA {
    @PrimaryKey()
    private int BASESTATION_ID;
    @ColumnInfo(name = "longitude")
    private float LONGITUDE;
    @ColumnInfo(name = "latitude")
    private float LATITUDE;
    @ColumnInfo(name = "networkid")
    private int NETWORK_ID;

    public int getBASESTATION_ID() {
        return BASESTATION_ID;
    }

    public void setBASESTATION_ID(int BASESTATION_ID) {
        this.BASESTATION_ID = BASESTATION_ID;
    }

    public float getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(float LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public float getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(float LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public int getNETWORK_ID() {
        return NETWORK_ID;
    }

    public void setNETWORK_ID(int NETWORK_ID) {
        this.NETWORK_ID = NETWORK_ID;
    }
}
