package whalethedvaa.whalethedvaa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poor_authentication_killer.*

abstract class PoorAuthenticationKiller : AppCompatActivity() {

    abstract var Numbers : IntArray
    var check: Boolean = true
    var point: Int = 0
    abstract var Operations: CharArray
    var point2: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poor_authentication_killer)
        Thread.setDefaultUncaughtExceptionHandler( MyExceptionHandler(this))

        if (getIntent().getBooleanExtra("crash", false)) {
            Toast.makeText(this, "Moby had his revenge", Toast.LENGTH_LONG).show()
            FlagText.text = "WH413L0RD"
        }

        val level = intent.getIntExtra("Level", 0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable


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
        EqualsBtn.setOnClickListener { numPadInput('=') }
        PlusBtn.setOnClickListener { numPadInput('+') }
        MinusBtn.setOnClickListener { numPadInput('-') }
        DivideBtn.setOnClickListener { numPadInput('/') }
        MultiBtn.setOnClickListener { numPadInput('X') }

        //Call function to reset the pin on screen
        ResetBtn.setOnClickListener { reset() }

        //call hint dialog creation function
        HintBtn.setOnClickListener { hintSelectionDialog() }

        //Call instructions dialog creation function
        InstructionsBtn.setOnClickListener { instructionsDialog() }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener { onBackPressed() }
        //instructionsDialog()


        //Flags button
        flagsBtn.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }



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
        PinOutput.text = ""
    }

    private fun numPadInput(num: Char) {
        val text: String = PinOutput.text.toString()
        val charText: CharArray = text.toCharArray()

        var a = 0
        when(num) {
            '=' ->{
                var final = Numbers[0]
                val length = point-1
                for (i in 1..length){
                    when(Operations[i]){
                        '+' -> final += Numbers[i]
                        '-' -> final -= Numbers[i]
                        '/' -> final /= Numbers[i]
                        'X' -> final *= Numbers[i]
                    }
                }
                PinOutput.text = final.toString()
            }

            '+','-' ,'X','/'  ->{
                if(check == false){
                    Toast.makeText(this, "This is not possible", Toast.LENGTH_SHORT).show()
                }else{
                    check == false
                    Operations[point2] = num
                    point2++
                        println(text)
                        println(num)
                        for (i in charText.indices) {
                            charText[i] = num
                        }

                        //println(charText.toString())
                        val newText = String(charText)

                        println(newText)
                        PinOutput.text = newText
                }
            }

            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> {
                a = Character.getNumericValue(num)
                if(check == true){
                    Numbers[point] *= 10
                    Numbers[point] += a
                } else {
                    check = true
                    point++
                    Numbers[point] *= 10
                    Numbers[point] += a
                }

                println(text)
                println(num)
                for (i in charText.indices) {
                    charText[i] = num
                }

                //println(charText.toString())
                val newText = String(charText)

                println(newText)
                PinOutput.text = newText

            }


        }
    }


}


