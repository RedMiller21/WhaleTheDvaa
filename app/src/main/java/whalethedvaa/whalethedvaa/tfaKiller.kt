package com.example.paulb.whale2fa

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_tfa_killer.*
import whalethedvaa.whalethedvaa.R
import java.text.SimpleDateFormat
import java.util.Calendar

class tfaKiller : Activity() {

    private var stampBool : Boolean = false
    private var code : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfa_killer)
        val t = object : Thread() {
            override fun run() {
                while (true) {
                    try {
                        Thread.sleep(1000)
                        runOnUiThread { updateTime() }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        t.start()
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

        btnCode.setOnClickListener{
            checkCode(txtCode.text.toString())
        }

        lblTime.setOnClickListener {
            stampBool = !stampBool
        }

        informationDialog()
    }

    private fun updateTime() {
        val cal = Calendar.getInstance()
        val df = SimpleDateFormat("HH:mm:ss")
        val time = df.format(cal.time)
        val stamp = System.currentTimeMillis() / 1000
        when{
            stampBool -> lblTime.text = stamp.toString()
            else -> lblTime.text = time
        }
        code = (stamp % 1000000)/ 100
    }

    private fun informationDialog(){
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("2FA Information")
        builder.setMessage("You are a hacker who has been sniffing your victim’s network, viewing the codes they have been using to log in to their account. You determine that the codes are not going up or down by a set value each time, but they are changing. Your findings from sniffing the network can be found in Hint 1.")
        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog(){
        // Initialize a new instance of
        val builder = android.support.v7.app.AlertDialog.Builder(this)
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
        val dialog: android.support.v7.app.AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun hintDialog(chosenHint: String)
    {

        val builder = android.support.v7.app.AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(chosenHint)
        when(chosenHint){
            "Hint 1" -> builder.setMessage("From sniffing the network, you can see that at 07:29:34 08/08/18 GMT, the code was 7133. At 20:15:23 12/11/2018 GMT, it was 5372.")
            "Hint 2" -> builder.setMessage("The Unix timestamp is the number of seconds that have passed since the 'epoch' - midnight 01/01/1970. Try clicking on the clock to find out more.")
            "Hint 3" -> builder.setMessage("The code is 4 digits long, so only part of the timestamp is used. The code changes every 100 seconds.")
        }

        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()

    }

    private fun checkCode(enteredCode : String){
        when (enteredCode){
            code.toString() -> codeResult("Success")
            else -> codeResult("Denied")
        }
    }

    private fun codeResult(result : String){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(result)
        when(result){
            "Success" -> builder.setMessage("Welcome back, Victim.\n*C4SC4D1NG*")
            "Denied" -> builder.setMessage("The code you entered was incorrect. Please try again.")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
