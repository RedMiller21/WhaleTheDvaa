package com.example.paulb.whale2fa

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_tfa_easy.*
import whalethedvaa.whalethedvaa.ProgressPage
import whalethedvaa.whalethedvaa.R
import java.util.*

class tfaEasy : AppCompatActivity() {

    internal var code = genCode()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tfa_easy)
        hideCodeInput()
        displayInfo(0)

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

        //Button to submit email
        btnEmail.setOnClickListener {
            enterEmail()
        }

        //Button to enter 2FA code
        btnCode.setOnClickListener {
            enterCode()
        }

        informationDialog()

        //Flags button
        flagsBtn.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }
    }


    fun dlgCode(): Dialog {
        val showCode = AlertDialog.Builder(this, R.style.whaleDialog)
        showCode.setTitle("New Email") //This dialog will appear as if it is an email to the user
        showCode.setMessage("Your code is $code") //This will provide them with their own 2FA code
            .setPositiveButton("Return") { dialog, _ -> dialog.dismiss() }
            .create()
        return showCode.show()
    }

    fun dlgVtm(): Dialog {
        val showCode = AlertDialog.Builder(this, R.style.whaleDialog)
        showCode.setTitle("Success!") //If the user logs in with the victim's code and email address
        showCode.setMessage("Welcome back, Victim. \n*P0K3RF4C3*") //they will get the flag
            .setPositiveButton("Return") { dialog, _ -> dialog.dismiss() }
            .create()
        return showCode.show()
    }

    fun dlgHkr(): Dialog {
        val showCode = AlertDialog.Builder(this, R.style.whaleDialog)
        showCode.setTitle("Success!")
        showCode.setMessage("Welcome back, Hacker. \nYour credentials are correct")
            .setPositiveButton("Return") { dialog, _ -> dialog.dismiss() }
            .create()
        return showCode.show()
    }

    private fun genCode(): String {
        val intCode = Random().nextInt(10000) //Generate random code between 0-9999
        val stCode: String
        if (intCode < 10) { //this if tree will create a string of the code, adding zeros to ensure it is 4 digits long
            stCode = "000$intCode"
            return stCode
        } else if (intCode < 100) {
            stCode = "00$intCode"
            return stCode
        } else if (intCode < 1000) {
            stCode = "0$intCode"
            return stCode
        }
        return Integer.toString(intCode) //if code is already 4 digits long, it will be passed as string
    }

    private fun hideCodeInput() { //Before an email address has been entered, the widgets used to enter the 2FA code will be hidden
        btnCode.visibility = View.GONE
        lblCode.visibility = View.GONE
        txtCode.visibility = View.GONE
    }

    private fun showCodeInput() { //After an email address is entered, the widgets used to enter the 2FA code are visible
        btnCode.visibility = View.VISIBLE
        lblCode.visibility = View.VISIBLE
        txtCode.visibility = View.VISIBLE
    }

    private fun enterEmail() { //Function to make decision based on username information
        when (txtEmail.text.toString()) {
            "victim@whalemail.sea" -> { //Victim's email address has been entered
                showCodeInput() //Show code input form
                displayInfo(1) //Say email has been sent
            }
            "hacker@whalemail.sea" -> {//Hacker's email address has been entered
                showCodeInput() //Show code input form
                dlgCode() //Display user's code
                displayInfo(1) //Say email has been sent
            }
            else -> displayInfo(2) //Other email or unknown input has been entered, display error message
        }
    }

    private fun displayInfo(option: Int) {
        when (option) {
            0 -> lblInfo.text = "" //Make form blank
            1 -> {
                lblInfo.setTextColor(Color.WHITE)
                lblInfo.text = "A login code has been sent to your email address!" //Success message
            }
            2 -> {
                lblInfo.setTextColor(Color.RED)
                lblInfo.text = "Email address could not be found. Please try again." //Error message - wrong email
            }
            3 -> {
                lblInfo.setTextColor(Color.RED)
                lblInfo.text = "Error: Wrong code was entered. Please try again." //Error message - wrong code
            }
        }
    }

    private fun enterCode() { //Function to check if 2FA code is correct
        if (code == txtCode.text.toString()) { //If code has been entered correctly
            when (txtEmail.text.toString()) {
                "victim@whalemail.sea" -> dlgVtm() //if victim email, display flag
                "hacker@whalemail.sea" -> dlgHkr() //if hacker email, display success message
                else -> displayInfo(2) //else, display error
            }
        } else {
            displayInfo(3) //if code is incorrect, display error message
        }
    }

    private fun informationDialog() {
        val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("2FA Information")
        builder.setMessage("You are trying to gain access to the account of ‘victim@whalemail.sea’, but you need this user’s 2FA code. After entering their email address, the user will be sent an email with the code they can use to log in. You have the email address ‘hacker@whalemail.sea’. Try to find your victim’s code and use it to log in to their account.")
        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog() {
        // Initialize a new instance of
        val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)
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
        val dialog: android.support.v7.app.AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun hintDialog(chosenHint: String) {

        val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(chosenHint)
        when (chosenHint) {
            "Hint 1" -> builder.setMessage("You have been given the email address 'hacker@whalemail.sea'. Try entering this username.")
            "Hint 2" -> builder.setMessage("Does the code change when you request it more than once? Do you think it is uniquely generated?")
            "Hint 3" -> builder.setMessage("You need to login in as Victim. You have their email address, but not their code. Or do you?")
        }

        val dialog: android.support.v7.app.AlertDialog = builder.create()
        dialog.show()

    }
}
