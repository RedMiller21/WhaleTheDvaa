package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poor_authentication.*

class PoorAuthentication : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poor_authentication)
        val level = intent.getIntExtra("Level",0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable

        //Call function to change pin on screen
        NumPad1.setOnClickListener{numPadInput('1')}
        NumPad2.setOnClickListener{numPadInput('2')}
        NumPad3.setOnClickListener{numPadInput('3')}
        NumPad4.setOnClickListener{numPadInput('4')}
        NumPad5.setOnClickListener{numPadInput('5')}
        NumPad6.setOnClickListener{numPadInput('6')}
        NumPad7.setOnClickListener{numPadInput('7')}
        NumPad8.setOnClickListener{numPadInput('8')}
        NumPad9.setOnClickListener{numPadInput('9')}

        //Call function to reset the pin on screen
        ResetBtn.setOnClickListener{reset()}

        //Call function to confirm pin
        ConfirmBtn.setOnClickListener {confirm()}

        //Call information dialog creation
        InformationBtn.setOnClickListener{informationDialog()}

        //call hint dialog creation function
        HintBtn.setOnClickListener{hintSelectionDialog()        }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }
    }

    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Hard Coding Information")
        builder.setMessage("Example Information")
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
            "Hint 1" -> builder.setMessage("Example hint 1")
            "Hint 2" -> builder.setMessage("Example hint 2")
            "Hint 3" -> builder.setMessage("Example hint 3")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun numPadInput(num: Char){
        val text: String = PinOutput.text.toString()
        val charText: CharArray = text.toCharArray()
        println(text)
        println(num)
        var i = 0
        while (i < 4) {
            if(charText[i] == '-'){
                charText[i] = num
                break
            }
            i++
        }
        println(i)

        //println(charText.toString())
        val newText = String(charText)

        println(newText)
        PinOutput.text = newText

    }

    private fun reset(){
        PinOutput.text = "----"
    }

    private fun confirm(){
        val text: String = PinOutput.text.toString()
        val pin = "4252"
        if (pin == text){
            Toast.makeText(this,"Correct", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,"WRONG", Toast.LENGTH_LONG).show()
        }
        PinOutput.text = "----"
    }
}

