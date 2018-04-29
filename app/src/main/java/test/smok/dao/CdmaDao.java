package test.smok.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import test.smok.enity.CDMA;

/**
 * Created by matthew on 21.04.18.
 */
@Dao
public interface CdmaDao {
    @Query("SELECT * FROM cdma ORDER BY BASESTATION_ID ASC")
    List<CDMA> getAll();

    @Update
    void update(CDMA cdma);
//    @Query("SELECT * FROM lte where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    User findByName(String firstName, String lastName);

//    @Query("SELECT COUNT(*) from user")
//    int countUsers();
    @Query("SELECT (EXISTS (SELECT * FROM cdma WHERE BASESTATION_ID = :BasestationID AND NETWORKID = :NetworkID AND LONGITUDE = :Longitude AND LATITUDE = :Latitude))")
    boolean checkIfExists(String BasestationID, String Latitude, String Longitude,String NetworkID);


    @Insert
    void insertAll(CDMA... cdmas) ;

    @Delete
    void delete(CDMA cdma);
}
