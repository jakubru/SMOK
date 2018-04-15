package test.smok.logic;

import android.content.Context;

/**
 * Created by Kuba on 15.04.2018.
 */

public abstract class Subsystem {

    private Context mContext;
    public Subsystem(Context context){
        this.mContext = context;
    }


    abstract public void react();

}
