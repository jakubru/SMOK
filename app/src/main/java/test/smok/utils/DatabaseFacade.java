package test.smok.utils;

import java.util.List;

import test.smok.database.AppDatabase;
import test.smok.enity.CDMA;
import test.smok.enity.GMS;
import test.smok.enity.LTE;
import test.smok.enity.WCDMA;

/**
 * Created by matthew on 21.04.18.
 */
public class DatabaseFacade {



    public void addLte(final AppDatabase db, LTE lte) {
        db.lteDao().insertAll(lte);
    }

    public void addGms(final AppDatabase db, GMS gms) {
        db.gmsDao().insertAll(gms);
    }
    public void addCdma(final AppDatabase db, CDMA cdma) {
        db.cdmaDao().insertAll(cdma);
    }
    public void addWcdma(final AppDatabase db, WCDMA wcdma) {
        db.wcdmaDao().insertAll(wcdma);
    }

    public void deleteGms(final AppDatabase db, GMS gms){
        db.gmsDao().delete(gms);
    }
    public void deleteCdma(final AppDatabase db, CDMA cdma){
        db.cdmaDao().delete(cdma);
    }
    public void deleteWcdma(final AppDatabase db, WCDMA wcdma){
        db.wcdmaDao().delete(wcdma);
    }
    public void deleteLte(final AppDatabase db, LTE lte){
        db.lteDao().delete(lte);
    }

    public List<GMS> getAllGMS(final AppDatabase db){
        return db.gmsDao().getAll();
    }
    public List<CDMA> getAllCDMA(final AppDatabase db){
        return db.cdmaDao().getAll();
    }
    public List<WCDMA> getAllWCDMA(final AppDatabase db){
        return db.wcdmaDao().getAll();
    }
    public List<LTE> getAllLTE(final AppDatabase db){
        return db.lteDao().getAll();
    }

    public float getBTSLongitude(final AppDatabase db, String CID){
        return db.gmsDao().getAccurateLongitude(CID);
    }

    public float getBTSLatitude(final AppDatabase db, String CID){
        return db.gmsDao().getAccurateLatitude(CID);
    }

    public boolean checkIfExistsGSM(final AppDatabase db, String cid, String lac, String mcc, String mnc){return db.gmsDao().checkIfExists(cid,lac,mcc,mnc);}
    public boolean checkIfExistsCDMA(final AppDatabase db, String bsid, String latitude, String longitude, String networkId){return db.cdmaDao().checkIfExists(bsid,latitude,longitude,networkId);}
    public boolean checkIfExistsLTE(final AppDatabase db, String ci, String tac, String pci, String mnc){return db.lteDao().checkIfExists(ci, mnc,pci,tac);}
    public boolean checkIfExistsWCDMA(final AppDatabase db, String cid, String lac, String mcc, String mnc){return db.wcdmaDao().checkIfExists(cid,lac,mcc,mnc);}

    public void updateGMS(final AppDatabase db, GMS gms){
        db.gmsDao().update(gms);
    }
    public void updateCDMA(final AppDatabase db, CDMA cdma){
        db.cdmaDao().update(cdma);
    }
    public void updateWCDMA(final  AppDatabase db, WCDMA wcdma){
        db.wcdmaDao().update(wcdma);
    }
    public void updateLTE(final AppDatabase db, LTE lte){
        db.lteDao().update(lte);
    }
}

