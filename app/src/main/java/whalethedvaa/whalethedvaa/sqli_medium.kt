

package whalethedvaa.whalethedvaa

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqli.*

class sqli_medium : AppCompatActivity() {
    //declaring an array of the database type emails
    //private val emails : ArrayList<Emails> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqli)
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
        for (email in emails) db.daoAccess().insertOnlySingleUser(email)

        //db?.daoAccess()?.insertOnlySingleUser(emailAdd, password)

        //todo this is fucked, assigning a textview to button resulting in runtime error - not fucked anymore
        SQL_Login.setOnClickListener{

            //simple validation of input using the most common SQLi statement
            var searchText = SQL_Email.text.toString()
            var badhacker : String  = "SELECT * FROM Emails WHERE uid = 2 OR 1=1"

            var validation = true;

            if (searchText == badhacker){
                validation = false
            }

            if (validation == false){
                Toast.makeText(applicationContext, "Bad hacker!!! Try again", Toast.LENGTH_SHORT).show()
            } else{
                val emailCount = db.daoAccess().emailExists(searchText)

                if (emailCount > 0) {
                    Toast.makeText(applicationContext, "This worked", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "didny work", Toast.LENGTH_SHORT).show()
                }
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
            "Hint 1" -> builder.setMessage("Test test test")
            "Hint 2" -> builder.setMessage("Example hint 2")
            "Hint 3" -> builder.setMessage("Example hint 3")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }



}
