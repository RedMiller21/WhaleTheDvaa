package whalethedvaa.whalethedvaa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_tfa_killer.*
import java.text.SimpleDateFormat
import java.util.*

class tfaKiller : Activity() {

    private var stampBool: Boolean = false //Boolean for if timestamp is being displayed
    private var code: Long = 0 //Initialise 2FA code
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfa_killer)
        val t = object : Thread() { //Start thread to update clock/timestamp every second
            override fun run() {
                while (true) {
                    try {
                        Thread.sleep(1000) //Wait 1 second
                        runOnUiThread { updateTime() } //Call function to update display
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        t.start()
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

        //Button to check if correct code has been entered
        btnCode.setOnClickListener {
            checkCode(txtCode.text.toString())
        }

        //When clock is clicked it will change to a timestamp and vice versa
        lblTime.setOnClickListener {
            stampBool = !stampBool //Invert bool for display
        }

        //Flags button move to progress page
        flagsBtn.setOnClickListener {
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }


        informationDialog()

        //Flags button
        flagsBtn.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }
    }

    private fun updateTime() {
        val cal = Calendar.getInstance() //Get current time
        val df = SimpleDateFormat("HH:mm:ss") //Create format for displaying time
        val time = df.format(cal.time)
        val stamp = System.currentTimeMillis() / 1000 //Timestamp is current time in seconds, i.e. milliseconds/1000
        when { //case statement determines what to display based on stampBool being true or false
            stampBool -> lblTime.text = stamp.toString() //if true, display time stamp
            else -> lblTime.text = time //if false, display clock time
        }
        code = (stamp % 1000000) / 100 //Moduli timestamp by 1000000 and divide by 100 to get 4 digits used for 2FA code
    }

    //Display vulnerability information
    private fun informationDialog() {
        val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("2FA Information")
        builder.setMessage("You are a hacker who has been sniffing your victim’s network, viewing the codes they have been using to log in to their account. You determine that the codes are not going up or down by a set value each time, but they are changing. Your findings from sniffing the network can be found in Hint 1.")
        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog() {
        val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)
        builder.setTitle("Hints")// Set the alert dialog title
        val hints = arrayOf("Hint 1", "Hint 2", "Hint 3") //Display options for hints
        //SET PROPERTIES USING METHOD CHAINING
        builder.setItems(hints) { _, which ->
            hintDialog(hints[which])
            println(hints[which])
        }

        // Finally, make the alert dialog using builder
        val dialog: android.support.v7.app.AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun hintDialog(chosenHint: String) {

        val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(chosenHint)
        when (chosenHint) {
            "Hint 1" -> builder.setMessage("From sniffing the network, you can see that at 07:29:34 08/08/2018 GMT, the code was 7133. At 20:15:23 12/11/2018 GMT, it was 5372.")
            "Hint 2" -> builder.setMessage("The Unix timestamp is the number of seconds that have passed since the 'epoch' - midnight 01/01/1970. Try clicking on the clock to find out more.")
            "Hint 3" -> builder.setMessage("The code is 4 digits long, so only part of the timestamp is used. The code changes every 100 seconds.")
        }

        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()

    }

    private fun checkCode(enteredCode: String) {
        when (enteredCode) {  //case statement comparing code entered by user
            code.toString() -> codeResult("Success") //if match, call codeResult with 'success'
            else -> codeResult("Denied") //otherwise, call with 'Denied'
        }
    }

    private fun codeResult(result: String) {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(result)
        when (result) {
            "Success" -> builder.setMessage("Welcome back, Victim.\n*C4SC4D1NG*") //If successful, display flag
            "Denied" -> builder.setMessage("The code you entered was incorrect. Please try again.") //If not successful, display error message
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
