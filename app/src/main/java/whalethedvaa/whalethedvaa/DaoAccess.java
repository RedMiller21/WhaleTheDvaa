package whalethedvaa.whalethedvaa;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


public interface DaoAccess {

    //example queries
    @Insert
    void insertOnlySingleUser(Emails emails);

    @Query("SELECT * FROM Emails WHERE uid=:uid")
    Emails fetchOneEmailsByEmailsID(int uid);

    @Update
    void updateEmails(Emails emails);

    @Delete
    void deleteEmail(Emails emails);

    @Query("SELECT COUNT(*) FROM Emails WHERE emailAddress=:searchText")
    int emailExists(String searchText);

    //@Query(searchText)
    //int emailExists(String searchText);

}
