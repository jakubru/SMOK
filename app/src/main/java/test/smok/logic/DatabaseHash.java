package test.smok.logic;

import android.content.Context;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import test.smok.database.AppDatabase;
import test.smok.enity.CDMA;
import test.smok.enity.GMS;
import test.smok.enity.LTE;
import test.smok.enity.WCDMA;
import test.smok.utils.DatabaseFacade;
import test.smok.utils.DatabaseMake;

/**
 * Created by Kuba on 30.04.2018.
 */

public class DatabaseHash {
    private DatabaseMake mDatabaseMake;
    private Context mContext;

    public DatabaseHash(Context context){
        this.mDatabaseMake = new DatabaseMake(new DatabaseFacade());
        this.mContext = context;
    }


    public String hashLTE(){
        List<LTE> list = mDatabaseMake.databaseFacade.getAllLTE(AppDatabase.getAppDatabase(this.mContext));
        int n = list.size();
        String string = "";
        for(int i = 0; i < n; i++){
            LTE lte = list.get(i);
            string += "" + lte.getCI() + lte.getMNC() + lte.getPCI() + lte.getTAC();
        }
        return hash(string);
    }

    public String hashGSM(){
        List<GMS> list = mDatabaseMake.databaseFacade.getAllGMS(AppDatabase.getAppDatabase(this.mContext));
        int n = list.size();
        String string = "";
        for(int i = 0; i < n; i++){
            GMS gms = list.get(i);
            string += "" + gms.getBSIC() + gms.getCID() + gms.getLAC() + gms.getMCC() + gms.getMNC() + gms.getPSC();
        }
        return hash(string);
    }

    public String hashCDMA(){
        List<CDMA> list = mDatabaseMake.databaseFacade.getAllCDMA(AppDatabase.getAppDatabase(this.mContext));
        int n = list.size();
        String string = "";
        for(int i = 0; i < n; i++){
            CDMA cdma = list.get(i);
            string += "" + cdma.getBASESTATION_ID() + cdma.getNETWORK_ID() + cdma.getLONGITUDE() + cdma.getLATITUDE();
        }
        return hash(string);
    }

    public String hashWCDMA(){
        List<WCDMA> list = mDatabaseMake.databaseFacade.getAllWCDMA(AppDatabase.getAppDatabase(this.mContext));
        int n = list.size();
        String string = "";
        for(int i = 0; i < n; i++){
            WCDMA wcdma = list.get(i);
            string += "" + wcdma.getCID() + wcdma.getLAC() + wcdma.getMCC() + wcdma.getMNC() + wcdma.getPSC();
        }
        return hash(string);
    }

    private String hash(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for(byte byte1 :messageDigest){
                String h = Integer.toHexString(0xFF & byte1);
                while (h.length() < 2){
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
