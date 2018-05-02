package test.smok.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import test.smok.enity.WCDMA;

/**
 * Created by matthew on 21.04.18.
 */
@Dao
public interface WcdmaDao {
    @Query("SELECT * FROM wcdma ORDER BY CID ASC")
    List<WCDMA> getAll();

//    @Query("SELECT * FROM lte where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    User findByName(String firstName, String lastName);

//    @Query("SELECT COUNT(*) from user")
//    int countUsers();

    @Query("SELECT (EXISTS (SELECT * FROM wcdma WHERE CID = :CID AND LAC = :LAC AND MCC = :MCC AND MNC = :MNC))")
    boolean checkIfExists(String CID, String LAC, String MCC, String MNC);

    @Update
    void update(WCDMA wcdma);
    @Insert
    void insertAll(WCDMA... wcdmas) ;

    @Delete
    void delete(WCDMA wcdma);
}
