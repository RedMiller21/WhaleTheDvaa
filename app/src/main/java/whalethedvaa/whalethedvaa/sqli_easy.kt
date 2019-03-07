package whalethedvaa.whalethedvaa

import android.arch.persistence.db.SimpleSQLiteQuery
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqli_easy.*


class sqli_easy : AppCompatActivity() {
    //declaring an array of the database type emails
    private val emails : ArrayList<Emails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqli_easy)
        val level = intent.getIntExtra("Level",0) //level is the difficulty setting 1 easy 2 medium and 3 hard
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

        var emails = Emails.populateData()
       // for (email in emails) {
         //   val query = SimpleSQLiteQuery(
           //     "INSERT INTO `Emails` (uid, emailAddress, password) VALUES(?, ?, ?)",
             //    arrayOf(email.uid, email.emailAddress, email.password))

           // db.rawDao().insertUser(query)
        //}

        SQL_Login.setOnClickListener{
            //no validation on search text
            var searchText = SQL_Email.text.toString()

            val query = SimpleSQLiteQuery(
                "SELECT COUNT(*) FROM Emails WHERE emailAddress= ?",
                arrayOf(searchText)
            )

            val email = db.rawDao().getUserEasy(query)

            if (email > 0.toString()) {
                val successText = "YAS CONGRATS"
                val textView: TextView = findViewById(R.id.txtSuccessMsg) as TextView
                textView.text = successText;
                //toast commented out for testing purposes
               // Toast.makeText(applicationContext, "This worked", Toast.LENGTH_SHORT).show()
            } else {
                val badtext = "Email is not linked to an account"
                val textView: TextView = findViewById(R.id.txtSuccessMsg) as TextView
                textView.text = badtext;
                Toast.makeText(applicationContext, "FLAG - B055M4N", Toast.LENGTH_SHORT).show()
            }
        }

        //Call information dialog creation
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        //call hint dialog creation function
        HintBtn.setOnClickListener{
            hintSelectionDialog()
        }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

    }

    private fun CheckEmail(searchText: String){
    }

    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("SQL Injection - Easy")
        builder.setMessage("You have been provided the email whale@whalemail.sea, use this login to gain as much information as possible." +
                "This level shows how user data is stored insecurely and poorly verified. This field does not implement a lockout policy alongside allowing account enumeration. Account enumeration allows a hacker to " +
                "identify active accounts by brute force, allowing active accounts to be easily identified. Show you understand how to enumerate accounts!")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog(){
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Hints")

        // Display a message on alert dialog
        //builder.setMessage("Which hint would you like")

        val hints = arrayOf("Hint 1", "Hint 2", "Hint 3")
        //SET PROPERTIES USING METHOD CHAINING
        builder.setItems(hints){ _, which ->
            hintDialog(hints[which])
            println(hints[which])
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun hintDialog(chosenHint: String)
    {

        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(chosenHint)
        when(chosenHint){
            "Hint 1" -> builder.setMessage("You have been given the email whale@whalemail.sea, try entering this on the login screen")
            "Hint 2" -> builder.setMessage("This page is vulnerable to account enumeration, remember that means you can show what emails exist and which do not!")
            "Hint 3" -> builder.setMessage("Enter fish@whalemail.sea, note what happens to your success message.")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }



}
