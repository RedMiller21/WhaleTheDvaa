package whalethedvaa.whalethedvaa

import android.arch.persistence.db.SupportSQLiteQuery
import android.arch.persistence.room.Dao
import android.arch.persistence.room.RawQuery
import whalethedvaa.whalethedvaa.Emails

@Dao
public interface RawDao {
    @RawQuery
    fun getUserEasy(queryEasy: SupportSQLiteQuery): String

    @RawQuery
    fun getUserMedium(queryMedium: SupportSQLiteQuery): Emails

    @RawQuery
    fun getUserKiller(queryKiller: SupportSQLiteQuery): Emails

    @RawQuery
    fun getUserViaQueryEasy(queryEasy: SupportSQLiteQuery): Emails

    @RawQuery
    fun getUserViaQueryMedium(queryMedium: SupportSQLiteQuery): Emails

    @RawQuery
    fun getUserViaQueryKiller(queryKiller: SupportSQLiteQuery): Emails

    @RawQuery
    fun insertUser(queryInsert: SupportSQLiteQuery): Emails
}