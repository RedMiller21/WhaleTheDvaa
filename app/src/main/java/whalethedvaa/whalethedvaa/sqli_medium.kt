

package whalethedvaa.whalethedvaa

import android.arch.persistence.db.SimpleSQLiteQuery
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqli.*

class sqli_medium : AppCompatActivity() {
    //declaring an array of the database type emails
    //private val emails : ArrayList<Emails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqli_medium)
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



        val headersText = "EMAIL       PASSWORD"
        val accountinfoone = "whale@whalemail.sea     .rÏ.îNlbÄC*Z"

        val textView: TextView = findViewById(R.id.txtSuccessMsg) as TextView
        textView.text = headersText;
        textView.text = accountinfoone;

        // todo this is fucked, assigning a textview to button resulting in runtime error - not fucked anymore
        SQL_Login.setOnClickListener{
            //todo change this to input
            var Encryption = SQL_Email.text.toString()

            if (Encryption == "MD5 Base64"){
                Toast.makeText(applicationContext, "FLAG - NICEW4N", Toast.LENGTH_SHORT).show()
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
            onBackPressed()
        }

    }

    private fun CheckEmail(searchText: String){
    }

    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Hard Coding Information")
        builder.setMessage("Example Information")
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

        val hints = arrayOf("Hint 1", "Hint 2", "Hint 3", "Hint 4")
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
            "Hint 1" -> builder.setMessage("You have been provided with the email whale@whalemail.sea, try entering this email into the login form")
            "Hint 2" -> builder.setMessage("This page is vulnerable to code injection, the table name is Emails and columns are emailAddress and password")
            "Hint 3" -> builder.setMessage("Remember you are trying to force the program to execute the query with a statement that will always return true")
            "Hint 4" -> builder.setMessage("Try entering SELECT * FROM Emails WHERE emailAddress = whale@whalemail.sea OR 5=5 AND 1=1 and see what happens to the success message")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }



}
