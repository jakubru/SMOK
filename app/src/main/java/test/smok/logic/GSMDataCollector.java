package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;

import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(18)
public class GSMDataCollector implements DataCollector {
    private TelephonyManager mTelephonyManager;

    @Override
    public String [] collect(Context context) {
        List<CellInfo> cellInfoList = null;
        String [] returnString;
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }
        returnString = new String[cellInfoList.size()];
        int i = 0;
        for (CellInfo cellInfo:cellInfoList)
        {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
            returnString[i++] = "NetworkType:GSM;CID:" + cellIdentityGsm.getCid() + ";LAC:" + cellIdentityGsm.getLac() /*+ ";ARFCN:" + cellIdentityGsm.getArfcn()*/  + ";MCC:" + cellIdentityGsm.getMcc()
                    + /*";BSIC:" + cellIdentityGsm.getBsic() + */ ";MNC:" + cellIdentityGsm.getMnc() + ";PSC:" + cellIdentityGsm.getPsc() + ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel() +
                    ";DBM" + cellSignalStrengthGsm.getDbm() + ";Level:" + cellSignalStrengthGsm.getLevel();/* + ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();*/
        }
        return returnString;
    }

    public int get1(Context context){
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyManager.getNetworkType();
    }
}
