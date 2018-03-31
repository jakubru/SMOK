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

    public GSMCellDataCollector(Context context){
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
        for (CellInfo cellInfo:cellInfoList)
        {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
            returnString += "NetworkType:GSM;CID:" + cellIdentityGsm.getCid() + ";LAC:" + cellIdentityGsm.getLac() /*+ ";ARFCN:" + cellIdentityGsm.getArfcn()*/  + ";MCC:" + cellIdentityGsm.getMcc()
                    + /*";BSIC:" + cellIdentityGsm.getBsic() + */ ";MNC:" + cellIdentityGsm.getMnc() + ";PSC:" + cellIdentityGsm.getPsc() + ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel() +
                    ";DBM" + cellSignalStrengthGsm.getDbm() + ";Level:" + cellSignalStrengthGsm.getLevel() + ";IMSI:"+ getIMSI() + "|";/* + ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();*/
        }
        return returnString;
    }
}
