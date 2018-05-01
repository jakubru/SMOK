package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;

import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(18)
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
        for (CellInfo cellInfo:cellInfoList)
        {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
            returnString += "NetworkType:GSM;CID:" + cellIdentityGsm.getCid() + ";LAC:" + cellIdentityGsm.getLac() /*+ ";ARFCN:" + cellIdentityGsm.getArfcn()*/  + ";MCC:" + cellIdentityGsm.getMcc()
                    + /*";BSIC:" + cellIdentityGsm.getBsic() + */ ";MNC:" + cellIdentityGsm.getMnc() + ";PSC:" + cellIdentityGsm.getPsc() + ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel() +
                    ";DBM:" + cellSignalStrengthGsm.getDbm() + ";Level:" + cellSignalStrengthGsm.getLevel() + "|";/* + ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();*/
        }
        return returnString;
    }
}
