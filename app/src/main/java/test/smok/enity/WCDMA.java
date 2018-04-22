package test.smok.enity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew on 21.04.18.
 */

@Entity(tableName = "wcdma")
public class WCDMA {
    @PrimaryKey()
    private int CID;
    @ColumnInfo(name = "longitude")
    private float LONGITUDE;
    @ColumnInfo(name = "latitude")
    private float LATITUDE;
    @ColumnInfo(name = "lac")
    private int LAC;
    @ColumnInfo(name = "mnc")
    private int MNC;
    @ColumnInfo(name = "mcc")
    private int MCC;
    @ColumnInfo(name = "psc")
    private int PSC;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
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

    public int getLAC() {
        return LAC;
    }

    public void setLAC(int LAC) {
        this.LAC = LAC;
    }

    public int getMNC() {
        return MNC;
    }

    public void setMNC(int MNC) {
        this.MNC = MNC;
    }

    public int getMCC() {
        return MCC;
    }

    public void setMCC(int MCC) {
        this.MCC = MCC;
    }

    public int getPSC() {
        return PSC;
    }

    public void setPSC(int PSC) {
        this.PSC = PSC;
    }
}
