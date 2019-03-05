package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_the_whala.*

//TODO: Get rid of the puns
class TheWhala : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_whala)

        GetSplashing.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

        InstuctionsBtn.setOnClickListener{instructionsDialog()}


    }
    private fun instructionsDialog(){
        val builder = AlertDialog.Builder(this)
        val subInstructions = arrayOf("What its whale about",  "The Vulnerabilities/Mitigations", "Flags/Progression", "All the buttons under the sea")
        builder.setTitle("Instructions")
            .setItems(subInstructions){ _, which ->
            instDialog(subInstructions[which])
            println(subInstructions[which])
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun instDialog(choosenInst: String)
    {

        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(choosenInst)
            .setNegativeButton("Back"){ _,_ -> instructionsDialog()}
        var subInstructions = arrayOf("null")
        when(choosenInst){
            "What its whale about" -> {
                builder.setMessage(R.string.AboutTheWhale)
            }
            "Flags/Progression" -> {
                subInstructions = arrayOf("Whats with the Flags", "The looks: Whats l33t speak")
            }
            "All the buttons under the sea" ->{
                subInstructions = arrayOf("Hint", "?")
            }
            "The Vulnerabilities/Mitigations" -> {
                subInstructions = arrayOf("Vulnerabilities", "Mitigations")
            }
        }
        if (!subInstructions.contains("null")){
            builder.setItems(subInstructions){ _, which ->
                furtherInstrDialog(subInstructions[which], choosenInst)
                println(subInstructions[which])
            }
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun furtherInstrDialog(choosenInst: String, lastInst: String)
    {
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle(choosenInst)
            .setNegativeButton("Back"){ _,_ -> instDialog(lastInst)}
        var subInstructions = arrayOf("null")
        when(choosenInst){
            "Hint" -> {
                builder.setMessage(R.string.hints)
            }
            "?" ->{
                builder.setMessage(R.string.information)
            }
            "Whats with the Flags" -> {
                builder.setMessage(R.string.flags)
            }
            "The looks: Whats l33t speak" -> {
                builder.setMessage(R.string.l33tSpeak)
            }
            "Vulnerabilities" -> {
                builder.setMessage(R.string.vulnerabilties)
            }
            "Mitigations" -> {
                builder.setMessage(R.string.mitigation)
            }
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}