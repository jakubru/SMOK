package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;


import java.lang.annotation.Target;
import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(26)
public class GSMDataCollector implements DataCollector {
    private TelephonyManager mTelephonyManager;

    public GSMDataCollector()
    {

    }

    @Override
    public String collect(Context context) {
        List<CellInfo> cellInfoList = null;
        String returnString = "bład";
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }
        for (CellInfo cellInfo:cellInfoList)
        {
            if(cellInfo instanceof CellInfoGsm)
            {
                CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
                CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
                returnString = "CID:" + cellIdentityGsm.getCid() + ";LAC:" + cellIdentityGsm.getLac() + ";ARFCN:" + cellIdentityGsm.getArfcn() + ";MCC:" + cellIdentityGsm.getMcc()
                + ";BSIC:" + cellIdentityGsm.getBsic() + ";MNC:" + cellIdentityGsm.getMnc() + ";PSC:" + cellIdentityGsm.getPsc() + ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel() +
                ";DBM" + cellSignalStrengthGsm.getDbm() + ";Level:" + cellSignalStrengthGsm.getLevel() + ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();
            }
            if(cellInfo instanceof CellInfoWcdma){
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
                CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                returnString = "CID:" + cellIdentityWcdma.getCid();/* + ";LAC:" + cellIdentityWcdma.getLac() + ";ARFCN:" + cellIdentityWcdma.getUarfcn() + ";MCC:" + cellIdentityWcdma.getMcc()
                        + ";MNC:" + cellIdentityWcdma.getMnc() + ";PSC:" + cellIdentityWcdma.getPsc() + ";AsuLevel:" + cellSignalStrengthWcdma.getAsuLevel() +
                        ";DBM" + cellSignalStrengthWcdma.getDbm() + ";Level:" + cellSignalStrengthWcdma.getLevel();*/
            }

        }
        return returnString;
    }
}
