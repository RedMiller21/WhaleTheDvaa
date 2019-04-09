package whalethedvaa.whalethedvaa

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sqli_hard.*


class sqli_hard : AppCompatActivity() {
    //declaring an array of the database type emails
    private val emails : ArrayList<Emails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqli_hard)
        val level = intent.getIntExtra("Level", 0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable


        // declare instance of database
        var db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "Email_Address"
        )
            //allows room to recreate database tables in main thread without locking UI
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        if (db.daoAccess().rowCount < 1)
        {
            var emails = Emails.populateData()
            for (email in emails) db.daoAccess().insertUserTest(email)
        }

        //for (email in emails) db.daoAccess().insertOnlySingleUser(email)

        SQL_Login.setOnClickListener {

            //val emailCount = db.daoAccess().emailExists(searchText)

            var statement = findViewById(R.id.SQL_Email) as EditText
            var successemail = findViewById(R.id.txtSuccessMsg) as TextView
            var successpassword = findViewById(R.id.txtSuccessMsg) as TextView
            val stringStatement = statement.text.toString()

            var SQLstatmentOne = "SELECT * FROM Emails WHERE uid = 1 OR 1=1"
            var SQLstatementTwo = "SELECT * FROM Emails WHERE uid = 0 OR 1=1"
            var SQLstatementThree = "SELECT uid, email_address, password FROM Emails uid, email_address, password FROM Emails WHERE uid = 1 or 1= WHERE uid = 105 or 1=1"


            /*if (db.daoAccess().rowCount > 0)
            {
                db.daoAccess().insertUserTest()
            }*/



            /**
             * if statements for three common SQL injection commands
             */
            if (stringStatement == SQLstatmentOne)
            {
                //query broken?? - temporary fix
                val test = db.daoAccess().fetchOneEmailsByEmailsID(1)
                //success.text = "whale@whalemail.sea   4M4Z1NG"
                //success.text = test.toString()

                successemail.text = test.emailAddress.toString()
                successpassword.text = test.password.toString()

            }

            if (stringStatement == SQLstatementTwo)
            {
                //query broken?? - temporary fix
                val test = db.daoAccess().fetchOneEmailsByEmailsID(1)
                //success.text = "whale@whalemail.sea   4M4Z1NG"
                //success.text = test.toString()

                successemail.text = test.emailAddress.toString()
                successpassword.text = test.password.toString()

            }

            if (stringStatement == SQLstatementThree)
            {
                //query broken?? - temporary fix
                val test = db.daoAccess().fetchOneEmailsByEmailsID(1)
                //success.text = "whale@whalemail.sea   4M4Z1NG"
                //success.text = test.toString()

                successemail.text = test.emailAddress.toString()
                successpassword.text = test.password.toString()

            }

        }

        //Call information dialog creation
        InformationBtn.setOnClickListener {
            informationDialog()
        }

        //call hint dialog creation function
        HintBtn.setOnClickListener {
            hintSelectionDialog()
        }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener {
            onBackPressed()
        }

        //Flags button
        flagsBtn.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }

    }

    private fun CheckEmail(searchText: String) {
    }

    private fun informationDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Hard Coding Information")
        builder.setMessage("Example Information")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog() {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Hints")

        // Display a message on alert dialog
        //builder.setMessage("Which hint would you like")

        val hints = arrayOf("Hint 1", "Hint 2", "Hint 3")
        //SET PROPERTIES USING METHOD CHAINING
        builder.setItems(hints) { _, which ->
            hintDialog(hints[which])
            println(hints[which])
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun hintDialog(chosenHint: String) {

        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(chosenHint)
        when (chosenHint) {
            "Hint 1" -> builder.setMessage("Table name is Emails with columns uid, email address and password")
            "Hint 2" -> builder.setMessage("This input field lacks validation, try entering an SQL statement")
            "Hint 3" -> builder.setMessage("Enter SELECT * FROM Emails WHERE uid = 1 OR 1=1")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }


}
