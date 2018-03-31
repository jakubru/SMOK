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
@TargetApi(18)
public class LTECellDataCollector extends CellDataCollector {

    public LTECellDataCollector(Context context){
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
        for (CellInfo cellInfo:cellInfoList ){
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
            CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
            returnString += "NetworkType:LTE;CI:" + cellIdentityLte.getCi() /*+ ";EARFCN:" + cellIdentityLte.getEarfcn()*/ + ";MCC:" + cellIdentityLte.getMcc() + ";MNC:" + cellIdentityLte.getMnc()
                    + ";PCI:" +  cellIdentityLte.getPci() + ";TAC:" + cellIdentityLte.getTac() + ";AsuLevel:" + cellSignalStrengthLte.getAsuLevel() /* + ";CQI:" + cellSignalStrengthLte.getCqi() */
                    + ";DBM:" + cellSignalStrengthLte.getDbm() + ";Level:" + cellSignalStrengthLte.getLevel() /*+ ";RSRP:" + cellSignalStrengthLte.getRsrp() + ";RSRQ:" + cellSignalStrengthLte.getRsrq()
                    + ";RSSNR:" + cellSignalStrengthLte.getRssnr()*/ + ";TimingAdvance:" + cellSignalStrengthLte.getTimingAdvance() + ";IMSI:"+ getIMSI() + "|";

        }

        return returnString;
    }
}
