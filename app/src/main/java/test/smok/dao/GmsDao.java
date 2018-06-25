package test.smok.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import test.smok.enity.GMS;


/**
 * Created by matthew on 21.04.18.
 */

@Dao
public interface GmsDao {
    @Query("SELECT * FROM gms ORDER BY CID ASC")
    List<GMS> getAll();


    @Query("SELECT (EXISTS (SELECT * FROM gms WHERE CID = :CID AND LAC = :LAC AND MCC = :MCC AND MNC = :MNC))")
    boolean checkIfExists(String CID, String LAC, String MCC, String MNC);

    @Query("SELECT LONGITUDE FROM GMS WHERE CID = :CID")
    float getAccurateLongitude(String CID);

    @Query("SELECT LATITUDE FROM GMS WHERE CID = :CID")
    float getAccurateLatitude(String CID);

    @Update
    void update(GMS gms);

    @Insert
    void insertAll(GMS... gms) ;

    @Delete
    void delete(GMS gms);
}
