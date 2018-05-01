package test.smok.logic;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthWcdma;

import java.util.List;

/**
 * Created by Kuba on 03.03.2018.
 */
@TargetApi(18)
public class WCDMACellDataCollector extends CellDataCollector {

    private static WCDMACellDataCollector mInstance;

    private WCDMACellDataCollector(Context context){
        super(context);
    }

    public static WCDMACellDataCollector getInstance(Context context){
        if(mInstance == null){
            mInstance = new WCDMACellDataCollector(context);
        }
        return mInstance;
    }

    @Override
    protected String [] getRegistered() {
        List<CellInfo> cellInfoList = null;
        String [] stringArray = new String[6];
        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }

        stringArray[0] = "WCDMA";
        for (CellInfo cellInfo: cellInfoList){
            if(cellInfo.isRegistered()){
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
                stringArray[1] = Integer.toString(cellIdentityWcdma.getCid());
                stringArray[2] = Integer.toString(cellIdentityWcdma.getLac());
                stringArray[3] = Integer.toString(cellIdentityWcdma.getMcc());
                stringArray[4] = Integer.toString(cellIdentityWcdma.getMnc());
                stringArray[5] = Integer.toString(cellIdentityWcdma.getPsc());
            }
        }

        return stringArray;
    }


    @Override
    protected String collect() {
        List<CellInfo> cellInfoList = null;
        String returnString;
        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }

        returnString = "";
        for (CellInfo cellInfo: cellInfoList){
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
            CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
            returnString += "NetworkType:WCDMA;CID:" + cellIdentityWcdma.getCid() + ";LAC:" + cellIdentityWcdma.getLac() /* + ";ARFCN:" + cellIdentityWcdma.getUarfcn() */ +  ";MCC:" + cellIdentityWcdma.getMcc()
                    + ";MNC:" + cellIdentityWcdma.getMnc() + ";PSC:" + cellIdentityWcdma.getPsc() + ";AsuLevel:" + cellSignalStrengthWcdma.getAsuLevel() +
                    ";DBM:" + cellSignalStrengthWcdma.getDbm() + ";Level:" + cellSignalStrengthWcdma.getLevel() + "|";
        }

        return returnString;
    }


}
