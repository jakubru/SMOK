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


    public WCDMACellDataCollector(Context context){
        super(context);

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
