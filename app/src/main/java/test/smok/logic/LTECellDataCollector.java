package test.smok.logic;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.CellSignalStrengthLte;

import java.util.List;

/**
 * Created by Kuba on 03.03.2018.
 */
@TargetApi(24)
public class LTECellDataCollector extends CellDataCollector {

    private static LTECellDataCollector mInstance;

    private LTECellDataCollector(Context context){
        super(context);
    }

    public static LTECellDataCollector getInstance(Context context){
        if(mInstance == null){
            mInstance = new LTECellDataCollector(context);
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
        stringArray[0] = "LTE";
        for (CellInfo cellInfo:cellInfoList ){
            if(cellInfo.isRegistered()){
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
                stringArray[1] = Integer.toString(cellIdentityLte.getCi());
                stringArray[2] = Integer.toString(cellIdentityLte.getMcc());
                stringArray[3] = Integer.toString(cellIdentityLte.getMnc());
                stringArray[4] = Integer.toString(cellIdentityLte.getPci());
                stringArray[5] = Integer.toString(cellIdentityLte.getTac());
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
        int i = 0;
        for (CellInfo cellInfo:cellInfoList ){
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
            CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
            returnString += "NetworkType:LTE;CI:" + cellIdentityLte.getCi();
            try {
                returnString += ";EARFCN:" + cellIdentityLte.getEarfcn();
            }catch(Exception e){}
            returnString += ";MCC:" + cellIdentityLte.getMcc();
            returnString += ";MNC:" + cellIdentityLte.getMnc();
            returnString += ";PCI:" +  cellIdentityLte.getPci();
            returnString += ";TAC:" + cellIdentityLte.getTac();
            returnString += ";AsuLevel:" + cellSignalStrengthLte.getAsuLevel();
            returnString += ";DBM:" + cellSignalStrengthLte.getDbm();
            returnString += ";Level:" + cellSignalStrengthLte.getLevel();
            returnString += ";TimingAdvance:" + cellSignalStrengthLte.getTimingAdvance() + "|";

        }

        return returnString;
    }
}
