package test.smok.logic;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellSignalStrengthCdma;

import java.util.List;

/**
 * Created by Kuba on 03.03.2018.
 */

@TargetApi(18)
public class CDMACellDataCollector extends CellDataCollector {

    private static CDMACellDataCollector mInstance;

    private CDMACellDataCollector(Context context){
        super(context);
    }

    public static CDMACellDataCollector getInstance(Context context){
        if(mInstance == null){
            mInstance = new CDMACellDataCollector(context);
        }
        return mInstance;
    }

    @Override
    protected String [] getRegistered() {
        List<CellInfo> cellInfoList = null;
        String [] stringArray = new String[5];

        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }

        stringArray [0] = "CDMA";

        for (CellInfo cellInfo: cellInfoList){
            if(cellInfo.isRegistered()) {
                CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
                stringArray[1] = Integer.toString(cellIdentityCdma.getBasestationId());
                stringArray[2] = Integer.toString(cellIdentityCdma.getLatitude());
                stringArray[3] = Integer.toString(cellIdentityCdma.getLongitude());
                stringArray[4] = Integer.toString(cellIdentityCdma.getNetworkId());
            }
        }
        return stringArray;
    }

    @Override
    protected String  collect() {
        List<CellInfo> cellInfoList = null;
        String returnString;

        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }

        returnString = "";

        int i = 0;

        for (CellInfo cellInfo: cellInfoList){
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
            CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
            returnString += "NetworkType:CDMA;BasestationID:" +  cellIdentityCdma.getBasestationId();
            returnString += ";Latitude:" +  cellIdentityCdma.getLatitude();
            returnString += ";Longitude:" + cellIdentityCdma.getLongitude();
            returnString += ";NetworkID:" + cellIdentityCdma.getNetworkId();
            returnString += ";SystemID:" + cellIdentityCdma.getSystemId();
            returnString += ";AsuLevel:" + cellSignalStrengthCdma.getAsuLevel();
            returnString += ";CDMADBM:" + cellSignalStrengthCdma.getCdmaDbm();
            returnString += ";CDMAECIO:" + cellSignalStrengthCdma.getCdmaEcio();
            returnString += ";CDMALevel:" + cellSignalStrengthCdma.getCdmaLevel();
            returnString += ";DBM:" + cellSignalStrengthCdma.getDbm();
            returnString += ";EVDODBM:" + cellSignalStrengthCdma.getEvdoDbm();
            returnString += ";EVDOECIO:" + cellSignalStrengthCdma.getEvdoEcio();
            returnString += ";EVDOLevel:" + cellSignalStrengthCdma.getEvdoLevel();
            returnString += ";EVDOSNR:" + cellSignalStrengthCdma.getEvdoSnr();
            returnString += ";Level:" + cellSignalStrengthCdma.getLevel() + "|";

        }
        return returnString;
    }



}
