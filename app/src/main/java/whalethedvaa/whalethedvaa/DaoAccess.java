package whalethedvaa.whalethedvaa;

import android.arch.persistence.room.*;


public @Dao
interface DaoAccess {

    @Insert
    void insertUserTest(Emails emails);

    @Query("SELECT * FROM Emails WHERE uid=:uid")
    Emails fetchOneEmailsByEmailsID(int uid);

    @Query("SELECT COUNT(emailAddress) FROM Emails")
    int getRowCount();
}

