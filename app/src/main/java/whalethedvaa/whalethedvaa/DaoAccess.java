package whalethedvaa.whalethedvaa;

import android.arch.persistence.room.Dao;


public @Dao
interface DaoAccess {


    //todo implement instances of classes
    //Emails isfoundeasy = RawDao.getUserEasy("SELECT COUNT(*) FROM Emails WHERE emailAddress=:searchText");
    //SimpleSQLiteQuery queryEasy = new SimpleSQLiteQuery("SELECT * FROM User WHERE id = ?", new Object[]{3});
    //Emails isfound1  = whalethedvaa.whalethedvaa.RawDao.getUserViaQueryEasy(queryEasy);

    //query for medium difficulty
    //Emails isfoundmedium = RawDao.getUserMedium("SELECT COUNT(*) FROM Emails WHERE emailAddress=:searchText");
    //SimpleSQLiteQuery queryMedium = new SimpleSQLiteQuery("SELECT * FROM User WHERE id = ?",
    //new Object[]{3});
    //Emails isfoundmedium2 = RawDao.getUserViaQueryMedium(queryMedium);

    //todo decide on Killer vulnerability setting/code
    //Emails isfoundKiller = RawDao.getUserKiller("");

    //@Insert
    //void insertOnlySingleUser(Emails emails);

    //@Query("SELECT * FROM Emails WHERE uid=:uid")
    //Emails fetchOneEmailsByEmailsID(int uid);

    //@Update
    //void updateEmails(Emails emails);

    //@Delete
    //void deleteEmail(Emails emails);

    //@Query("SELECT COUNT(*) FROM Emails WHERE emailAddress=:searchText")
    //int emailExists(String searchText);

    //@Query(searchText)
    //int emailExists(String searchText);

}

