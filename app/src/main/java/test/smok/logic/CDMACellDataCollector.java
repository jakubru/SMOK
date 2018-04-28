package test.smok.logic;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellSignalStrengthCdma;

import java.util.List;

/**
 * Created by Kuba on 03.03.2018.
 */

@TargetApi(18)
public class CDMACellDataCollector extends CellDataCollector {

    public CDMACellDataCollector(Context context){
        super(context);

    }

    @Override
    protected String getRegistered() {
        List<CellInfo> cellInfoList = null;
        String returnString;

        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();
        }
        catch(SecurityException e){

        }

        returnString = "";

        for (CellInfo cellInfo: cellInfoList){
            if(cellInfo.isRegistered()) {
                CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
                CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
                returnString += "NetworkType:CDMA;BasestationID:" + cellIdentityCdma.getBasestationId() + ";Latitude:" + cellIdentityCdma.getLatitude() + ";Longitude:" + cellIdentityCdma.getLongitude()
                        + ";NetworkID:" + cellIdentityCdma.getNetworkId() + ";SystemID:" + cellIdentityCdma.getSystemId() + ";AsuLevel:" + cellSignalStrengthCdma.getAsuLevel()
                        + ";CDMADBM:" + cellSignalStrengthCdma.getCdmaDbm() + ";CDMAECIO:" + cellSignalStrengthCdma.getCdmaEcio() + ";CDMALevel:" + cellSignalStrengthCdma.getCdmaLevel()
                        + ";DBM:" + cellSignalStrengthCdma.getDbm() + ";EVDODBM:" + cellSignalStrengthCdma.getEvdoDbm() + ";EVDOECIO:" + cellSignalStrengthCdma.getEvdoEcio()
                        + ";EVDOLevel:" + cellSignalStrengthCdma.getEvdoLevel() + ";EVDOSNR:" + cellSignalStrengthCdma.getEvdoSnr() + ";Level:" + cellSignalStrengthCdma.getLevel() + "|";
            }
        }
        return returnString;
    }

    @Override
    protected String  collect() {
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
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
            CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
            returnString += "NetworkType:CDMA;BasestationID:" +  cellIdentityCdma.getBasestationId() + ";Latitude:" +  cellIdentityCdma.getLatitude() + ";Longitude:" + cellIdentityCdma.getLongitude()
                    + ";NetworkID:" + cellIdentityCdma.getNetworkId() + ";SystemID:" + cellIdentityCdma.getSystemId() + ";AsuLevel:" + cellSignalStrengthCdma.getAsuLevel()
                    + ";CDMADBM:" + cellSignalStrengthCdma.getCdmaDbm() + ";CDMAECIO:" + cellSignalStrengthCdma.getCdmaEcio() + ";CDMALevel:" + cellSignalStrengthCdma.getCdmaLevel()
                    + ";DBM:" + cellSignalStrengthCdma.getDbm()+ ";EVDODBM:" + cellSignalStrengthCdma.getEvdoDbm() + ";EVDOECIO:" + cellSignalStrengthCdma.getEvdoEcio()
                    + ";EVDOLevel:" + cellSignalStrengthCdma.getEvdoLevel() + ";EVDOSNR:" + cellSignalStrengthCdma.getEvdoSnr() + ";Level:" + cellSignalStrengthCdma.getLevel() + "|";

        }
        return returnString;
    }



}
