package test.smok.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import test.smok.enity.LTE;

/**
 * Created by matthew on 21.04.18.
 */

@Dao
public interface LteDao {
    @Query("SELECT * FROM lte ORDER BY ci ASC")
    List<LTE> getAll();

//    @Query("SELECT * FROM lte where first_name LIKE  :firstName AND last_name LIKE :lastName")
//    User findByName(String firstName, String lastName);

//    @Query("SELECT COUNT(*) from user")
//    int countUsers();

    @Query("SELECT (EXISTS (SELECT * FROM lte WHERE CI = :CI AND TAC = :TAC AND MNC = :MNC AND PCI = :PCI))")
    boolean checkIfExists(String CI, String MNC,String PCI,String TAC);

    @Update
    void update(LTE lte);
    @Insert
    void insertAll(LTE... ltes) ;

    @Delete
    void delete(LTE lte);
}
