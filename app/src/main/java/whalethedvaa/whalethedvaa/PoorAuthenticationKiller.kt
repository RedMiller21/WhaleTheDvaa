package whalethedvaa.whalethedvaa

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poor_authentication_killer.*

class PoorAuthenticationKiller : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poor_authentication_killer)
        Thread.setDefaultUncaughtExceptionHandler( MyExceptionHandler(this));

        if (getIntent().getBooleanExtra("crash", false)) {
            Toast.makeText(this, "Moby had his revenge", Toast.LENGTH_LONG).show();
            OutputText.text = "WH413L0RD"
        }

        val level = intent.getIntExtra("Level", 0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable

        //Call function to reset the pin on screen
        ResetBtn.setOnClickListener { reset() }

        //Call function to confirm pin
        ConfirmBtn.setOnClickListener { confirm() }

        //call hint dialog creation function
        HintBtn.setOnClickListener { hintSelectionDialog() }

        //Call instructions dialog creation function
        InstructionsBtn.setOnClickListener { instructionsDialog() }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener { onBackPressed() }
        //instructionsDialog()
    }

    fun crashMe(v : View) {
        throw  NullPointerException()
    }

    private fun instructionsDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Poor Authentication Instructions")
            .setMessage(R.string.paminstructions)
            .setCancelable(false)
            .setNegativeButton(R.string.exit) { dialog, _ -> dialog.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Create dialog with hint options
    private fun hintSelectionDialog() {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Hints")

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
        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.hintimagepa, null)

        // Set the alert dialog title
        builder.setTitle(chosenHint)
        when (chosenHint) {
            "Hint 1" -> {
                builder.setMessage(R.string.hint1MPA)
                    .setTitle(chosenHint)
            }
            "Hint 2" -> {
                builder.setMessage(R.string.hint2MPA)
                    .setTitle(chosenHint)
            }
            "Hint 3" -> builder.setView(promptsView)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun reset() {
        InputText.getText().clear()
    }

    private fun confirm(){
        val text: String = InputText.text.toString()
        if(text == "Moby"){
            Toast.makeText(this, "Moby hasn't crashed the ship yet", Toast.LENGTH_SHORT).show();
        } else if (text == "Pokemon"){
            Toast.makeText(this, "Gotta! Catch! EM'ALL!", Toast.LENGTH_SHORT).show();
        }
        InputText.getText().clear()
    }

}

