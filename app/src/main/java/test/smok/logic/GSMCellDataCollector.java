package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;

import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(24)
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
        String [] stringArray = new String[7];
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
                //stringArray[4] = cellIdentityGsm.getBsic();
                stringArray[5] = Integer.toString(cellIdentityGsm.getMnc());
                stringArray[6] = Integer.toString(cellIdentityGsm.getPsc());
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

        Method[] method = CellIdentityGsm.class.getMethods();
        for (CellInfo cellInfo:cellInfoList)
        {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
            returnString += "NetworkType:GSM;CID:" + cellIdentityGsm.getCid();
            returnString += ";LAC:" + cellIdentityGsm.getLac();
            try {
                returnString += ";ARFCN:" + cellIdentityGsm.getArfcn();
            }catch(Exception e){}
            returnString += ";MCC:" + cellIdentityGsm.getMcc();
                    /*+ ";BSIC:" + cellIdentityGsm.getBsic() */
            returnString += ";MNC:" + cellIdentityGsm.getMnc();
            returnString += ";PSC:" + cellIdentityGsm.getPsc();
            returnString += ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel();
            returnString += ";DBM:" + cellSignalStrengthGsm.getDbm();
            returnString += ";Level:" + cellSignalStrengthGsm.getLevel() + "|";/* + ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();*/
        }
        return returnString;
    }
}
