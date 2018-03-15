package test.smok.logic;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Created by Kuba on 03.03.2018.
 */

@TargetApi(18)
public class CDMADataCollector implements DataCollector {
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

        for (CellInfo cellInfo: cellInfoList){
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
            CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
            returnString[i++] = "NetworkType:CDMA;BasestationID:" +  cellIdentityCdma.getBasestationId() + ";Latitude:" +  cellIdentityCdma.getLatitude() + ";Longitude:" + cellIdentityCdma.getLongitude()
                    + ";NetworkID:" + cellIdentityCdma.getNetworkId() + ";SystemID:" + cellIdentityCdma.getSystemId() + ";AsuLevel:" + cellSignalStrengthCdma.getAsuLevel()
                    + ";CDMADBM:" + cellSignalStrengthCdma.getCdmaDbm() + ";CDMAECIO:" + cellSignalStrengthCdma.getCdmaEcio() + ";CDMALevel:" + cellSignalStrengthCdma.getCdmaLevel()
                    + ";DBM:" + cellSignalStrengthCdma.getDbm()+ ";EVDODBM:" + cellSignalStrengthCdma.getEvdoDbm() + ";EVDOECIO:" + cellSignalStrengthCdma.getEvdoEcio()
                    + ";EVDOLevel:" + cellSignalStrengthCdma.getEvdoLevel() + ";EVDOSNR:" + cellSignalStrengthCdma.getEvdoSnr() + ";Level" + cellSignalStrengthCdma.getLevel();

        }

        return returnString;
    }

}
