package test.smok.logic;
import android.annotation.TargetApi;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;

/**
 * Created by Kuba on 16.12.2017.
 */
@TargetApi(17)
public class GSMDataCollector implements DataCollector {

    private CellInfoGsm cig;


    @Override
    public String collect() {
        CellIdentityGsm cellIdentityGsm = cig.getCellIdentity();
        CellSignalStrengthGsm cellSignalStrengthGsm = cig.getCellSignalStrength();
        return cellIdentityGsm.getCid() + "";
    }
}
