package test.smok.logic;
import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellIdentityCdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.TelephonyManager;


import java.lang.annotation.Target;
import java.util.List;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(18)
public class GSMDataCollector implements DataCollector {
    private TelephonyManager mTelephonyManager;

    public GSMDataCollector()
    {

    }

    @Override
    public String [] collect(Context context) {
        List<CellInfo> cellInfoList = null;
        String [] returnString = null;
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try{
            cellInfoList = mTelephonyManager.getAllCellInfo();


            returnString = new String[cellInfoList.size()];

            int i = 0;
            /*TODO
            Zakomentowane metody nie działają prawdopodobnie z powodu zbyt niskiego API Level na telefonie, lub coś gdzieś jest źle ustawione w projekcie
             */

            for (CellInfo cellInfo:cellInfoList)
            {
                if(cellInfo instanceof CellInfoGsm)
                {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    CellIdentityGsm cellIdentityGsm = cellInfoGsm.getCellIdentity();
                    CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();
                    returnString[i] = "NetworkType:GSM;CID:" + cellIdentityGsm.getCid() + ";LAC:" + cellIdentityGsm.getLac() /*+ ";ARFCN:" + cellIdentityGsm.getArfcn()*/  + ";MCC:" + cellIdentityGsm.getMcc()
                            + /*";BSIC:" + cellIdentityGsm.getBsic() + */ ";MNC:" + cellIdentityGsm.getMnc() + ";PSC:" + cellIdentityGsm.getPsc() + ";AsuLevel:" + cellSignalStrengthGsm.getAsuLevel() +
                            ";DBM" + cellSignalStrengthGsm.getDbm() + ";Level:" + cellSignalStrengthGsm.getLevel();/* + ";TimingAdvance:" + cellSignalStrengthGsm.getTimingAdvance();*/
                }
                else if(cellInfo instanceof CellInfoWcdma){
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    CellIdentityWcdma cellIdentityWcdma = cellInfoWcdma.getCellIdentity();
                    CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                    returnString[i] = "NetworkType:WCDMA;CID:" + cellIdentityWcdma.getCid() + ";LAC:" + cellIdentityWcdma.getLac() /* + ";ARFCN:" + cellIdentityWcdma.getUarfcn() */ +  ";MCC:" + cellIdentityWcdma.getMcc()
                            + ";MNC:" + cellIdentityWcdma.getMnc() + ";PSC:" + cellIdentityWcdma.getPsc() + ";AsuLevel:" + cellSignalStrengthWcdma.getAsuLevel() +
                            ";DBM:" + cellSignalStrengthWcdma.getDbm() + ";Level:" + cellSignalStrengthWcdma.getLevel();
                }

                else if (cellInfo instanceof CellInfoCdma){
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentityCdma = cellInfoCdma.getCellIdentity();
                    CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();

                    returnString[i] = "NetworkType:CDMA;BasestationID:" +  cellIdentityCdma.getBasestationId() + ";Latitude:" +  cellIdentityCdma.getLatitude() + ";Longitude:" + cellIdentityCdma.getLongitude()
                            + ";NetworkID:" + cellIdentityCdma.getNetworkId() + ";SystemID:" + cellIdentityCdma.getSystemId() + ";AsuLevel:" + cellSignalStrengthCdma.getAsuLevel()
                            + ";CDMADBM:" + cellSignalStrengthCdma.getCdmaDbm() + ";CDMAECIO:" + cellSignalStrengthCdma.getCdmaEcio() + ";CDMALevel:" + cellSignalStrengthCdma.getCdmaLevel()
                            + ";DBM:" + cellSignalStrengthCdma.getDbm()+ ";EVDODBM:" + cellSignalStrengthCdma.getEvdoDbm() + ";EVDOECIO:" + cellSignalStrengthCdma.getEvdoEcio()
                            + ";EVDOLevel:" + cellSignalStrengthCdma.getEvdoLevel() + ";EVDOSNR:" + cellSignalStrengthCdma.getEvdoSnr() + ";Level" + cellSignalStrengthCdma.getLevel();
                }

                else if(cellInfo instanceof CellInfoLte){
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
                    CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                    returnString[i] = "NetworkType:LTE;CI:" + cellIdentityLte.getCi() /*+ ";EARFCN:" + cellIdentityLte.getEarfcn()*/ + ";MCC:" + cellIdentityLte.getMcc() + ";MNC:" + cellIdentityLte.getMnc()
                            + ";PCI:" +  cellIdentityLte.getPci() + ";TAC:" + cellIdentityLte.getTac() + ";AsuLevel:" + cellSignalStrengthLte.getAsuLevel() /* + ";CQI:" + cellSignalStrengthLte.getCqi() */
                            + ";DBM:" + cellSignalStrengthLte.getDbm() + ";Level:" + cellSignalStrengthLte.getLevel() /*+ ";RSRP:" + cellSignalStrengthLte.getRsrp() + ";RSRQ:" + cellSignalStrengthLte.getRsrq()
                            + ";RSSNR:" + cellSignalStrengthLte.getRssnr()*/ + ";TimingAdvance:" + cellSignalStrengthLte.getTimingAdvance();

                }

                else{
                    returnString[i] = "NetworkType:Unknown;";
                }
                i++;
            }
        }
        catch(SecurityException e){

        }
        return returnString;
    }
}
