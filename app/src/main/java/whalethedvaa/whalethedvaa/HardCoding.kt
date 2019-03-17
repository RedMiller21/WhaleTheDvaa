package whalethedvaa.whalethedvaa

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_hard_coding.*

class HardCoding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_coding)
        val level = intent.getIntExtra("Level",0) //Read in the difficulty selection

        fun readFromAssets(){

            var fileName = "default"

            when(level) {                                           //Uses different txt files for the three difficulties
                1 -> fileName = "HardCoding_1_Easy.txt"
                2 -> fileName = "HardCoding_2_Medium.txt"
                3 -> fileName = "HardCoding_3_Killer.txt"
            }

            //This code reads in the text using a buffered reader
            val content = application.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
            //Then this code sets the text to the textview
            val textView: TextView = findViewById(R.id.txtField) as TextView
            textView.text = content

            //Some mild error catching
            if (fileName == "default"){
                textView.text = "//ERROR// - - //DIFF_SELECT_HARDCODE//"
            }

        }
        informationDialog()     //OnCreate, pop up the information dialog
        readFromAssets()        //OnCreate, read the assets file

        //Setting Buttons to Listeners
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        HintBtn.setOnClickListener{
            hintSelectionDialog()
        }

        BackBtn.setOnClickListener{
            onBackPressed()
        }
    }

    //Function to set the Info section of the code
    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        val level = intent.getIntExtra("Level",0)

        // Set the dialog title
        builder.setTitle("Hard Coding Information")

        //Code to change the info for the different difficulty levels
        if (level == 1){
            builder.setMessage("")
        }
        if (level == 2){
            builder.setMessage("")
        }
        if (level == 3){
            builder.setMessage("")
        }

        //Creating the dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog to show the hints
    private fun hintSelectionDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the dialog title
        builder.setTitle("Hints")

        //Using chaining to set the values of the hints
        val hints = arrayOf("Hint 1", "Hint 2", "Hint 3")
        builder.setItems(hints){ _, which ->
            hintDialog(hints[which])
        }

        //Build the Dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //Function to create the hint dialog
    private fun hintDialog(chosenHint: String)
    {
        val level = intent.getIntExtra("Level",0)
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(chosenHint)

        //The code below sets the Hints to different things depending on the difficulty selection
        if(level == 1) {
            when (chosenHint) {
                "Hint 1" -> builder.setMessage("")
                "Hint 2" -> builder.setMessage("")
                "Hint 3" -> builder.setMessage("")
            }
        }
        if(level == 2) {
            when (chosenHint) {
                "Hint 1" -> builder.setMessage("")
                "Hint 2" -> builder.setMessage("")
                "Hint 3" -> builder.setMessage("")
            }
        }
        if(level == 3) {
            when (chosenHint) {
                "Hint 1" -> builder.setMessage("")
                "Hint 2" -> builder.setMessage("")
                "Hint 3" -> builder.setMessage("")
            }
        }

        //Create the dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
