package test.smok.logic;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Kuba on 14.04.2018.
 */

public class MyIntentService2 extends IntentService {

    private CellDataCollector mCellDataCollector;

    /*Sprawdzanie czy bts, do którego jesteśmy aktualnie podłączeni znajduje się w bazie danych*/

    public MyIntentService2(){
        super("MyIntentService2");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
