package com.example.paulb.whale2fa

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tfa_medium.*
import whalethedvaa.whalethedvaa.R
import java.util.*

class tfaMedium : AppCompatActivity() {

    private val netlog = arrayOfNulls<String>(4) //Array(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfa_medium)
        val level = intent.getIntExtra("Level",2) //level is the difficulty setting 1
        // easy 2 medium and 3 hard
        //println(level) //comment out, debug for level variable

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

        val firstCode = Random().nextInt(10000)
        val addToCode = Random().nextInt(10000)
        for (i in 0..3) {
            netlog[i] = (convCode((firstCode + addToCode*(i+1)) % 10000))
        }

        informationDialog()

    }

    private fun ntlgString():String{
        val sb = StringBuilder()
        for (i in 0..2) {
            sb.append(netlog[i]).append("\n")
        }
        return sb.toString()
    }


    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("2FA Information")
        builder.setMessage(R.string.sqliMInfo)
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
            "Hint 1" -> builder.setMessage("The network log found the following entries: \n" + ntlgString())
            "Hint 2" -> builder.setMessage("The new code is calculated by adding a number to the last one. The codes go up by this same value each time.")
            "Hint 3" -> builder.setMessage("The codes are always 4 digits long. This means that if the result of the calculation is more than 4 digits, any digits above the thousands will be removed. For example, if the last code was 0001, and the codes go up by 9999, the next code will be 0000.")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun convCode(intCode : Int):String{
        val stCode : String
        when {
            intCode < 10 -> stCode = "000" + Integer.toString(intCode)
            intCode < 100 -> stCode = "00" + Integer.toString(intCode)
            intCode < 1000 -> stCode = "0" + Integer.toString(intCode)
            else -> stCode = Integer.toString(intCode)
        }
        return stCode
    }

    private fun checkCode(enteredCode : String){
        when (enteredCode){
            netlog[3].toString() -> codeResult("Success")
            else -> codeResult("Denied")
        }
    }

    private fun codeResult(result : String){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(result)
        when(result){
            "Success" -> builder.setMessage("Welcome back, Victim.\n*H0N3YB33*")
            "Denied" -> builder.setMessage("The code you entered was incorrect. Please try again.")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}