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
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        val level = intent.getIntExtra("Level",0)

        // Set the dialog title
        builder.setTitle("Hard Coding Information")

        //Code to change the info for the different difficulty levels
        if (level == 1){
            builder.setMessage("You've been supplied with the code for an android application which connects to a database for use internally. To find the flag you must look through the code and follow it's operations to find any security flaws.")
        }
        if (level == 2){
            builder.setMessage("For this exercise, you've been granted access to a small section of code from a malicious android application. To find the flag here, take a look at how the code interacts with the android operating system to further itself.")
        }
        if (level == 3){
            builder.setMessage("This final exercise will test knowledge worked on in the previous difficulties, you've been given another code segment from a malicious android application. Complete it by looking at what the code is doing to gather information on the user.")
        }

        //Creating the dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog to show the hints
    private fun hintSelectionDialog(){
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
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
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(chosenHint)

        //The code below sets the Hints to different things depending on the difficulty selection
        if(level == 1) {
            when (chosenHint) {
                "Hint 1" -> builder.setMessage("The android method for connecting and using a database uses hardcoded variables to interact with. These variables may contain sensitive information.")
                "Hint 2" -> builder.setMessage("Follow the flow of instructions when the application interacts with the database, values are hardcoded which contain the information you're looking for.")
                "Hint 3" -> builder.setMessage("Look closely at the bottom section of the code. This section is where the flag is added to the database.")
            }
        }
        if(level == 2) {
            when (chosenHint) {
                "Hint 1" -> builder.setMessage("Android malware will sometimes focus on elevating it's privilege so that it can access more functions within the android platform. Look at how the malware does this.")
                "Hint 2" -> builder.setMessage("Usually malware will use Command and Control (or C&C) servers to connect 'back home'. Finding out how the malicious code actually achieves this will allow security analysts to investigate the attacker further, and help you find the flag!")
                "Hint 3" -> builder.setMessage("The Class 'reportServiceState()' is where the malware reports back to the server, the flag is located in these server connection details!")
            }
        }
        if(level == 3) {
            when (chosenHint) {
                "Hint 1" -> builder.setMessage("This malware focuses on collecting information from the victims device, again, by looking at how the malware interacts with the OS you will find the flag.")
                "Hint 2" -> builder.setMessage("Just like the previous activity, this malware connects to a C&C server, albeit in a different manner, the information used to connect back could help find the flag.")
                "Hint 3" -> builder.setMessage("The section where the malware encodes information using URLEncoder, contains details on the author of the malicious code, and also the flag!")
            }
        }

        //Create the dialog
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}