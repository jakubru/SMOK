package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;

import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(17)
public class GSMCellDataCollector extends CellDataCollector {

    private static GSMCellDataCollector mInstance;

    private GSMCellDataCollector(Context context){
        super(context);
    }

    public static GSMCellDataCollector getInstance(Context context){
        if(mInstance == null){
            mInstance = new GSMCellDataCollector(context);
        }
        return mInstance;
    }


    @Override
    protected String [] getRegistered() {
        List<CellInfo> cellInfoList = null;
        String [] stringArray = new String[8];
        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }
        stringArray[0] = "GSM";
        for (CellInfo cellInfo:cellInfoList)
        {
            if(cellInfo.isRegistered()){
                CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
                stringArray[1] = Integer.toString(cellIdentityGsm.getCid());
                stringArray[2] = Integer.toString(cellIdentityGsm.getLac()) ;
                stringArray[3] = Integer.toString(cellIdentityGsm.getMcc());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    stringArray[4] = Integer.toString(cellIdentityGsm.getBsic());
                }
                else{
                    stringArray[4] = "";
                }
                stringArray[5] = Integer.toString(cellIdentityGsm.getMnc());
                stringArray[6] = Integer.toString(cellIdentityGsm.getPsc());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    stringArray[7] = Integer.toString(cellInfoGsm.getCellSignalStrength().getTimingAdvance());
                }
                else{
                    stringArray[7] = "-1";
                }
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

        for (CellInfo cellInfo:cellInfoList)
        {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
            returnString += "NetworkType:GSM;CID:" + cellIdentityGsm.getCid();
            returnString += ";LAC:" + cellIdentityGsm.getLac();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                returnString += ";ARFCN:" + cellIdentityGsm.getArfcn();
            }
            returnString += ";MCC:" + cellIdentityGsm.getMcc();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                returnString +=  ";BSIC:" + cellIdentityGsm.getBsic();
            }
            returnString += ";MNC:" + cellIdentityGsm.getMnc();
            returnString += ";PSC:" + cellIdentityGsm.getPsc();
            returnString += ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel();
            returnString += ";DBM:" + cellSignalStrengthGsm.getDbm();
            returnString += ";Level:" + cellSignalStrengthGsm.getLevel() + "|";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                returnString += ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();
            }

        }
        return returnString;
    }
}
