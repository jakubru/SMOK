package test.smok.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import test.smok.dao.CdmaDao;
import test.smok.dao.GmsDao;
import test.smok.dao.LteDao;
import test.smok.dao.WcdmaDao;
import test.smok.enity.CDMA;
import test.smok.enity.GMS;
import test.smok.enity.LTE;
import test.smok.enity.WCDMA;

/**
 * Created by matthew on 21.04.18.
 */


@Database(entities = {LTE.class, GMS.class, CDMA.class, WCDMA.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract LteDao lteDao();
    public abstract GmsDao gmsDao();
    public abstract CdmaDao cdmaDao();
    public abstract WcdmaDao wcdmaDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "client-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}