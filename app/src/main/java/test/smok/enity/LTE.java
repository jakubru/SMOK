package test.smok.enity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by matthew on 21.04.18.
 */

@Entity(tableName = "lte")
public class LTE {
    @PrimaryKey()
    private int CI;
    @ColumnInfo(name = "longitude")
    private float LONGITUDE;
    @ColumnInfo(name = "latitude")
    private float LATITUDE;
    @ColumnInfo(name = "mnc")
    private int MNC;
    @ColumnInfo(name = "pci")
    private int PCI;
    @ColumnInfo(name = "tac")
    private int TAC;

    public int getCI() {
        return CI;
    }

    public void setCI(int CI) {
        this.CI = CI;
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

    public int getMNC() {
        return MNC;
    }

    public void setMNC(int MNC) {
        this.MNC = MNC;
    }

    public int getPCI() {
        return PCI;
    }

    public void setPCI(int PCI) {
        this.PCI = PCI;
    }

    public int getTAC() {
        return TAC;
    }

    public void setTAC(int TAC) {
        this.TAC = TAC;
    }
}

