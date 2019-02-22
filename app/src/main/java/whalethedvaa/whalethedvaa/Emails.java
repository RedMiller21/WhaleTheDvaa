package whalethedvaa.whalethedvaa;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//declaration of user entity, table fields and instance
@Entity
public class Emails {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "emailAddress")
    private String emailAddress;

    @ColumnInfo(name = "password")
    private String password;

    //declaring main table
    public Emails(int uid, String emailAddress, String password){
        this.uid = uid;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    //for debugging - testing POJO to fetch the users email and password
   public class NameTuple{
        @ColumnInfo(name =  "emailAddress")
        public String emailaddress;

        @ColumnInfo(name = "password")
       public String password;
   }
    //automatic generation of getters and setter functions
    @NonNull
    public int getUid() {
        return uid;
    }

    public void setUid(@NonNull int uid) {
        this.uid = uid;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //populating database
    public static Emails[] populateData(){
        return new Emails[]{
                new Emails(1, "whale@whalemail.sea", "B055M4N"),
                new Emails(2, "whale@whalemail.sea", "N1C3-W4N"),
                new Emails(3, "whale@whalemail.sea", "4M4Z1NG")
        };
    }
}
