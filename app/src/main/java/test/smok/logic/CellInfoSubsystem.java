package test.smok.logic;

import android.content.Context;

/**
 * Created by Kuba on 15.04.2018.
 */

public class CellInfoSubsystem extends Subsystem {

    private CellDataCollector mCellDataCollector;

    public CellInfoSubsystem(CellDataCollector cellDataCollector, Context context){
        super(context);
        this.mCellDataCollector = cellDataCollector;
    }

    @Override
    public void react() {

    }
}
