package whalethedvaa.whalethedvaa;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;


public @Dao
interface DaoAccess {

   // @Insert
    //void insertOnlySingleUser(int uid, String emailAddress, String password);

    @Insert
    void insertUserTest(Emails emails);

    @Query("SELECT * FROM Emails WHERE uid=:uid")
    Emails fetchOneEmailsByEmailsID(int uid);

    @Update
    void updateEmails(Emails emails);

    @Delete
    void deleteEmail(Emails emails);

    @Query("SELECT COUNT(emailAddress) FROM Emails")
    int getRowCount();

    //@Query("SELECT COUNT(*) FROM Emails WHERE emailAddress=:searchText")
    //int emailExists(String searchText);

    //@Query(searchText)
    //int emailExists(String searchText);


    //@Query("SELECT * FROM emails")
    //Emails fetchemails(Emails emails);
}

