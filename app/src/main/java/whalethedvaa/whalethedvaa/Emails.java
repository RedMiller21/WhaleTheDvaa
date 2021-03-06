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
    public Emails(int uid, String emailAddress, String password) {
        this.uid = uid;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    /**
     * populating database
     *
     * @return
     */
    public static Emails[] populateData() {
        return new Emails[]{
                new Emails(1, "whale@whalemail.sea", "B055M4N"),
                new Emails(2, "whale@whalemail.sea", "N1C3-W4N"),
                new Emails(3, "whale@whalemail.sea", "4M4Z1NG")
        };
    }

    /**
     * automatic generation of getters and setter functions
     *
     * @return uid
     */
    @NonNull
    public int getUid() {
        return uid;
    }

    /**
     * @param uid based within the class
     */
    public void setUid(@NonNull int uid) {
        this.uid = uid;
    }

    /**
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * sets the email address to the class
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * gets the password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password of the class
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //for debugging - testing POJO to fetch the users email and password
    public class NameTuple {
        @ColumnInfo(name = "emailAddress")
        public String emailaddress;

        @ColumnInfo(name = "password")
        public String password;
    }
}
