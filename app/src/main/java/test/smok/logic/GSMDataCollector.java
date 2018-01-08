package test.smok.logic;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import android.util.SparseIntArray;
import android.view.View;


import java.lang.annotation.Target;
import java.util.List;

import test.smok.MainActivity;
import test.smok.R;


/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(18)
public class GSMDataCollector implements DataCollector {
    private TelephonyManager mTelephonyManager;
    private static final int REQUEST_PERMISSIONS = 124;
    private SparseIntArray mErrorString;
    private AppCompatActivity view;
    private boolean hasAllPermissions = false;
    public GSMDataCollector(AppCompatActivity view)
    {
        this.view = view;
        mErrorString = new SparseIntArray();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            hasAllPermissions = true;
        } else {
            hasAllPermissions = false;
            Snackbar.make(view.findViewById(android.R.id.content), mErrorString.get(requestCode),
                    Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + view.getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            view.startActivity(intent);
                        }
                    }).show();
        }
    }

    @Override
    public String [] collect(Context context) {
        requestAppPermissions(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                R.string.runtime_permissions_txt,
                REQUEST_PERMISSIONS);
        String[] result = null;
        if(hasAllPermissions){
            result = collectGSM(context);
        }
        return result;

    }
    private String [] collectGSM(Context context) {
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
        return returnString;
    }
    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(view, permission);
            if(permissionCheck<=0){
                int tmp = permissionCheck;
            }
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(view, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                hasAllPermissions = false;
                Snackbar.make(view.findViewById(android.R.id.content), stringId,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(view, requestedPermissions, requestCode);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(view, requestedPermissions, requestCode);
            }
        } else {
            hasAllPermissions = true;
        }
    }
}
