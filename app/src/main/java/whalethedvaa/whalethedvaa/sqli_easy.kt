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
import org.w3c.dom.Text


class sqli_easy : AppCompatActivity() {
    //declaring an array of the database type emails
    private val emails: ArrayList<Emails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqli_easy)
        val level = intent.getIntExtra("Level", 0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable

        informationDialog()

        // declare instance of database
        var db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "Email_Address"
        )
            //allows room to recreate database tables in main thread without locking UI
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        SQL_Login.setOnClickListener {
            //no validation on search text
            var searchText = findViewById<TextView>(R.id.SQL_Email)

            var email = "whale@whalemail.sea"

            val stringText = searchText.toString();
          //  val email = db.rawDao().getUserEasy(query)

            if (stringText == email) {
                val successText = "YAS CONGRATS"
                val textView: TextView = findViewById<TextView>(R.id.txtSuccessMsg)
                textView.text = successText
            } else {
                val badtext = "Email is not linked to an account" +
                        "Flag - 4M4Z1NG"
                val textView: TextView = findViewById<TextView>(R.id.txtSuccessMsg)
                textView.text = badtext

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
        BackBtn.setOnClickListener { onBackPressed() }

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
        builder.setTitle("SQL Injection - Easy")
        builder.setMessage(R.string.sqliEInfo)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog() {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Hints")

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
            "Hint 1" -> builder.setMessage(R.string.sqliEHintOne)
            "Hint 2" -> builder.setMessage(R.string.sqliEHintTwo)
            "Hint 3" -> builder.setMessage(R.string.sqliEHintThree)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }


}
