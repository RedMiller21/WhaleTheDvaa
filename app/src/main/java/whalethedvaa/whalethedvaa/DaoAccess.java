package whalethedvaa.whalethedvaa;

import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.room.*;


public @Dao interface DaoAccess {

    //example queries

//    @Dao
//    interface RawDao {
//        @RawQuery
//        User getUserViaQuery(SupportSQLiteQuery query);
//    }
//    SimpleSQLiteQuery query = new SimpleSQLiteQuery("SELECT * FROM User WHERE id = ? LIMIT 1",
//            new Object[]{userId});
//    User user2 = rawDao.getUserViaQuery(query);

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

