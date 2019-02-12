package com.example.paulb.whale2fa

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_tfa_easy.*
import whalethedvaa.whalethedvaa.R

import java.util.Random

class tfaEasy : AppCompatActivity() {

    internal var code = genCode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfa_easy)
        //final int code = new Random().nextInt(10000);
        hideCodeInput()
        displayInfo(0)

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


    fun dlgCode(): Dialog {
        val showCode = AlertDialog.Builder(this)
        showCode.setTitle("New Email")
        showCode.setMessage("Your code is $code")
            .setPositiveButton("Return") { dialog, which -> dialog.dismiss() }
            .create()
        return showCode.show()
    }

    fun dlgVtm(): Dialog {
        val showCode = AlertDialog.Builder(this)
        showCode.setTitle("Success!")
        showCode.setMessage("Welcome back, Victim. \n*p0k3rf4c3*")
            .setPositiveButton("Return") { dialog, which -> dialog.dismiss() }
            .create()
        return showCode.show()
    }

    fun dlgHkr(): Dialog {
        val showCode = AlertDialog.Builder(this)
        showCode.setTitle("Success!")
        showCode.setMessage("Welcome back, Hacker. \nYour credentials are correct")
            .setPositiveButton("Return") { dialog, which -> dialog.dismiss() }
            .create()
        return showCode.show()
    }

    private fun genCode(): String {
        val intCode = Random().nextInt(10000)
        val stCode: String
        if (intCode < 10) {
            stCode = "000$intCode"
            return stCode
        } else if (intCode < 100) {
            stCode = "00$intCode"
            return stCode
        } else if (intCode < 1000) {
            stCode = "0$intCode"
            return stCode
        }
        return Integer.toString(intCode)
    }

    private fun hideCodeInput() {
        btnCode.visibility = View.GONE
        lblCode.visibility = View.GONE
        txtCode.visibility = View.GONE
    }

    private fun showCodeInput() {
        btnCode.visibility = View.VISIBLE
        lblCode.visibility = View.VISIBLE
        txtCode.visibility = View.VISIBLE
    }

    fun enterEmail(v: View) {
        when (txtEmail.text.toString()) {
            "victim@whalemail.com" -> {
                showCodeInput()
                displayInfo(1)
            }
            "hacker@whalemail.com" -> {
                showCodeInput()
                dlgCode()
                displayInfo(1)
            }
            else -> displayInfo(2)
        }
    }

    private fun displayInfo(option: Int) {
        when (option) {
            0 -> lblInfo.text = ""
            1 -> {
                lblInfo.setTextColor(Color.WHITE)
                lblInfo.text = "A login code has been sent to your email address!"
            }
            2 -> {
                lblInfo.setTextColor(Color.RED)
                lblInfo.text = "Email address could not be found. Please try again."
            }
            3 -> {
                lblInfo.setTextColor(Color.RED)
                lblInfo.text = "Error: Wrong code was entered. Please try again."
            }
        }
    }

    fun enterCode(v: View) {
        //String enteredCode = txtCode.getText().toString();
        if (code == txtCode.text.toString()) {
            when (txtEmail.text.toString()) {
                "victim@whalemail.com" -> dlgVtm()
                "hacker@whalemail.com" -> dlgHkr()
                else -> displayInfo(2)
            }
        } else {
            displayInfo(3)
        }
    }

    private fun informationDialog(){
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("2FA Information")
        builder.setMessage("2FA codes should be unique for each user. A 2FA token acts as a unique identifier for the user logging in, so is redundant if it is not unique to them. The code should be randomly generated each time as to avoid repetition or the chances of this token falling into the wrong hands and being used by an attacker to access a user's account.")
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
            "Hint 1" -> builder.setMessage("You have been given the email address 'hacker@whalemail.com'. Try entering this username.")
            "Hint 2" -> builder.setMessage("Does the code change when you request it more than once? Do you think it is uniquely generated?")
            "Hint 3" -> builder.setMessage("You need to login in as Victim. You have their email address, but not their code. Or do you?")
        }

        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()

    }
}
