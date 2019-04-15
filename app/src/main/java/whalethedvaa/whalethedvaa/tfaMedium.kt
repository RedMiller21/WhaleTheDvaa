package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tfa_medium.*
import java.util.*

class tfaMedium : AppCompatActivity() {

    private val netlog = arrayOfNulls<String>(4) //Initialise array to store codes found in network log
    private var firstCode = 0 //initialise var for first code
    private var addToCode = 0 //initialise var for the amount codes go up by each time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfa_medium)

        var mTfaCodes = getSharedPreferences("tfaM", 0) //Get values from shared preferences
        firstCode = mTfaCodes.getInt("firstCode", 0) //Get first code
        addToCode = mTfaCodes.getInt("addToCode", 0) //Get amount to add to codes
        //If these cannot be found, they will default to 0. If this happens, they will be randomly generated
        // and these new codes will be stored with shared preferences.
        if (firstCode == 0 || addToCode == 0) { //If codes haven't been made
            resetCodes() //Create codes
        } else {
            calcCodes() //Else, calculate the 4 codes by adding the 'add to' amount to the first code
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

        //Button to check if entered code is correct
        btnCode.setOnClickListener {
            checkCode(txtCode.text.toString())
        }

        //Button to reset 2FA codes
        btnReset.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.whaleDialog)
            // Set the alert dialog title
            builder.setTitle("Generate new codes")
                .setMessage("Are you sure you want to reset the existing codes?") //Display dialog to check the user wants to change the codes
                .setPositiveButton("Yes") { _, _ ->
                    resetCodes() //If yes, call function to reset codes
                }
                .setNegativeButton("No") { dialog, _ -> dialog.cancel() } //If no, return to app
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        informationDialog()

        //Flags button
        flagsBtn.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }
    }

    private fun resetCodes() { //Function to regenerate 2FA codes
        var mTfaCodes = getSharedPreferences("tfaM", 0) //Open shared preferences used to store codes
        firstCode = Random().nextInt(10000) //Generate first code
        addToCode = Random().nextInt(10000) //Generate addition value
        val mEditor = mTfaCodes.edit()
        mEditor.putInt("firstCode", firstCode).apply() //Commit these values to shared preferences
        mEditor.putInt("addToCode", addToCode).apply()
        calcCodes() //Calculate remaining codes
    }

    private fun calcCodes() { //Function to calculate codes found in network log
        for (i in 0..3) {
            netlog[i] =
                (convCode((firstCode + addToCode * (i + 1)) % 10000)) //Calculate 2FA codes by adding the addition value
        }
    }

    private fun ntlgString(): String { //Generate network log as string to display
        val sb = StringBuilder()
        for (i in 0..2) { //Loop for 3 codes in network log
            sb.append(netlog[i]).append("\n") //Add code and new line to network log
        }
        return sb.toString() //Return network log as string
    }


    private fun informationDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("2FA Information")
        builder.setMessage(R.string.sqliMInfo)
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
            "Hint 1" -> builder.setMessage("The network log found the following entries: \n" + ntlgString())
            "Hint 2" -> builder.setMessage("The new code is calculated by adding a number to the last one. The codes go up by this same value each time.")
            "Hint 3" -> builder.setMessage("The codes are always 4 digits long. This means that if the result of the calculation is more than 4 digits, any digits above the thousands will be removed. For example, if the last code was 0001, and the codes go up by 9999, the next code will be 0000.")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun convCode(intCode: Int): String { //Function to ensure codes are 4 digits long
        val stCode: String
        when { //Case statement to add zeros as necessary and return string of code value
            intCode < 10 -> stCode = "000" + Integer.toString(intCode)
            intCode < 100 -> stCode = "00" + Integer.toString(intCode)
            intCode < 1000 -> stCode = "0" + Integer.toString(intCode)
            else -> stCode = Integer.toString(intCode)
        }
        return stCode //Return string result
    }

    private fun checkCode(enteredCode: String) { //Function to check if user has entered correct code
        when (enteredCode) { //Case statement to compare code entered by user
            netlog[3].toString() -> codeResult("Success") //If correct, call function to display flag
            else -> codeResult("Denied") //Else, error message
        }
    }

    private fun codeResult(result: String) { //Function to display result dialog
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(result)
        when (result) {
            "Success" -> {
                builder.setMessage("Welcome back, Victim.\n*H0N3YB33*"); resetCodes()
            } //If code is correct, display flag and reset codes
            "Denied" -> builder.setMessage("The code you entered was incorrect. Please try again.") //Else, display error message
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}