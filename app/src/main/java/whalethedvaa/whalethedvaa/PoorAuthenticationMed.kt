package whalethedvaa.whalethedvaa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poor_authentication_med.*

class PoorAuthenticationMed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constructionDialog()

        setContentView(R.layout.activity_poor_authentication)
        val level = intent.getIntExtra("Level", 0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable
        var pin: String = setPin()


        //Call function to change pin on screen
        NumPad1.setOnClickListener { numPadInput('1') }
        NumPad2.setOnClickListener { numPadInput('2') }
        NumPad3.setOnClickListener { numPadInput('3') }
        NumPad4.setOnClickListener { numPadInput('4') }
        NumPad5.setOnClickListener { numPadInput('5') }
        NumPad6.setOnClickListener { numPadInput('6') }
        NumPad7.setOnClickListener { numPadInput('7') }
        NumPad8.setOnClickListener { numPadInput('8') }
        NumPad9.setOnClickListener { numPadInput('9') }
        NumPad0.setOnClickListener { numPadInput('0') }

        //Call function to reset the pin on screen
        ResetBtn.setOnClickListener { reset() }

        //Call function to confirm pin
        ConfirmBtn.setOnClickListener { pin = confirm(pin) }

        //call hint dialog creation function
        HintBtn.setOnClickListener { hintSelectionDialog() }

        //Call instructions dialog creation function
        //InstructionsBtn.setOnClickListener { instructionsDialog() }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener { onBackPressed() }

        //Flags button
        flagsBtn.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }

        //instructionsDialog()
    }

    private fun instructionsDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Poor Authentication Instructions")
            .setMessage(R.string.paminstructions)
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

    private fun numPadInput(num: Char) {
        val text: String = PinOutput.text.toString()
        val charText: CharArray = text.toCharArray()
        println("text" + text)
        println("num" + num)
        var i = 0
        while (i < 4) {
            if (charText[i] == '-') {
                charText[i] = num
                break
            }
            i++
        }
        println("i" + i)

        //println(charText.toString())
        val newText = String(charText)

        println("newTest" + newText)
        PinOutput.text = newText

    }

    private fun reset() {
        PinOutput.text = "----"
    }

    private fun confirm(pin: String): String {
        val text: String = PinOutput.text.toString()
        var pinReturn: String = "0000"
        if (pin == text) {
            Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show()
            FlagText.text = "C4PTB1RD23Y3" //TODO: C4PTB1RD23Y3
            pinReturn = newPin()
        } else {
            Toast.makeText(this, "WRONG", Toast.LENGTH_LONG).show()
            pinReturn = pin
        }
        PinOutput.text = "----"
        println("pinReturn" + pinReturn)
        return pinReturn
    }

    private fun viewState(): Int {
        val sharedPreferences = getSharedPreferences("appInfo", Context.MODE_PRIVATE)
        val st = sharedPreferences.getInt("stateKey", 0)
        println("st" + st)
        return st
    }

    private fun setPin(): String {
        var pin = "0000"
        when (viewState()) {
            1 -> pin = "8721"
            2 -> pin = "5757"
            3 -> pin = "2581"
            4 -> pin = "3587"
            5 -> pin = "2278"
        }
        println("pin" + pin)
        return pin
    }

    private fun newPin(): String {
        val st = (1..5).random()
        val sharedPreferences = getSharedPreferences("appInfo", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("stateKey", st)
        editor.apply()
        return setPin()
    }

    private fun constructionDialog(){
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("UNDER CONSTRUCTION")
        builder.setMessage("This area is currently under construction, so it currently does not work! Luckily the flag is right here! - C4PTB1RD23Y3")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}

