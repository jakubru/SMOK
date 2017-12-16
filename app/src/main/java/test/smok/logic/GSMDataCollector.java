package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;


import java.lang.annotation.Target;
import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(17)
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
                returnString = "CID:";
            }
        }
        return returnString;
    }
}
