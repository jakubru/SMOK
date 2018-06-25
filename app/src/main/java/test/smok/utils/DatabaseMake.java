package test.smok.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import test.smok.database.AppDatabase;

/**
 * Created by matthew on 21.04.18.
 */

public class DatabaseMake {

    public static DatabaseFacade databaseFacade;

    public DatabaseMake(DatabaseFacade databaseFacade) {
        this.databaseFacade=databaseFacade;
    }

    public void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }

    }
}
