package whalethedvaa.whalethedvaa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_insecure_logging.*

class InsecureLogging : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insecure_logging)
        val level = intent.getIntExtra("Level",0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable

        fun readFromAssets(){

            var fileName = "default"

            when(level) {

                1 -> fileName = "InsecureLogging_1_Easy.txt"
                2 -> fileName = "InsecureLogging_3_Killer.txt"
                3 -> fileName = "InsecureLogging_2_Medium.txt"

            }

            val content = application.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
            val textView: TextView = findViewById(R.id.txtField) as TextView
            textView.text = content

            if (fileName == "default"){
                textView.text = "//ERROR// - - //DIFF_SELECT_LOGGING//"
            }


        }
        readFromAssets()

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

        //test

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

            "Hint 1" -> builder.setMessage("Example hint 1")
            "Hint 2" -> builder.setMessage("Example hint 2")
            "Hint 3" -> builder.setMessage("Example hint 3")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}
