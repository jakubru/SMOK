package test.smok.logic;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by Kuba on 16.12.2017.
 */
public abstract class CellDataCollector implements DataCollector {
    protected TelephonyManager mTelephonyManager;
    protected CellDataCollector nextCellDataCollector;
    protected abstract String collect();
    public CellDataCollector(Context context){
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String collectData(){
        try{
            return collect();
        }
        catch(Exception e){
            return nextCellDataCollector.collectData();
        }
    }

    public void setNextCollector(CellDataCollector cellDataCollector){
        this.nextCellDataCollector = cellDataCollector;
    }

    protected String getIMSI(){
        try{
            return this.mTelephonyManager.getSubscriberId();
        }
        catch(SecurityException e){
            return "";
        }
    }
}
