package whalethedvaa.whalethedvaa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_the_whala.*


//TODO: Get rid of the puns
//TODO: Don't get rid of the puns <3
//TODO: Never get rid of the puns!

class TheWhala : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_whala)

        GetSplashing.setOnClickListener {
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

        InstructionsBtn.setOnClickListener { instructionsDialog() }
        setup()


        TheWhale.setOnClickListener { whaleChange() }
    }

    private fun whaleChange() {
        var state = 0
        val sharedPreferences = getSharedPreferences("Whale", Context.MODE_PRIVATE)
        val st = sharedPreferences.getInt("whaleState", 0)
        val editor = sharedPreferences.edit()
        when (st) {
            0 -> {
                state = 1
                TheWhale.setImageResource(R.drawable.med1base)
            }
            1 -> {
                state = 2
                TheWhale.setImageResource(R.drawable.kill1base)
            }
            2 -> {
                state = 3
                TheWhale.setImageResource(R.drawable.whale_main)
            }
            3 -> {
                state = 4
                TheWhale.setImageResource(R.drawable.redhat)
            }
            4 -> {
                state = 5
                TheWhale.setImageResource(R.drawable.bertha)
            }
            5 -> {
                state = 6
                TheWhale.setImageResource(R.drawable.bluehat)
            }
            6 -> {
                state = 7
                TheWhale.setImageResource(R.drawable.clown)
            }
            7 -> {
                state = 8
                TheWhale.setImageResource(R.drawable.greenhat)
            }
            8 -> {
                state = 0
                TheWhale.setImageResource(R.drawable.police)
            }
        }
        editor.putInt("whaleState", state)
        editor.apply()
        editor.commit()
    }

    private fun instructionsDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        val subInstructions = arrayOf(
            "What its whale about",
            "The Vulnerabilities/Mitigations",
            "Flags/Progression",
            "All the buttons under the sea"
        )
        builder.setTitle("Instructions")
            .setItems(subInstructions) { _, which ->
                instDialog(subInstructions[which])
                println(subInstructions[which])
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun instDialog(chosenInst: String) {

        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(chosenInst)
            .setNegativeButton("Back") { _, _ -> instructionsDialog() }
        var subInstructions = arrayOf("null")
        when (chosenInst) {
            "What its whale about" -> {
                builder.setMessage(R.string.AboutTheWhale)
            }
            "Flags/Progression" -> {
                subInstructions = arrayOf("Whats with the Flags", "The looks: Whats l33t speak")
            }
            "All the buttons under the sea" -> {
                subInstructions = arrayOf("Hints", "Info", "Flags")
            }
            "The Vulnerabilities/Mitigations" -> {
                subInstructions = arrayOf("Vulnerabilities", "Mitigations")
            }
        }
        if (!subInstructions.contains("null")) {
            builder.setItems(subInstructions) { _, which ->
                furtherInstrDialog(subInstructions[which], chosenInst)
                println(subInstructions[which])
            }
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun furtherInstrDialog(choosenInst: String, lastInst: String) {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle(choosenInst)
            .setNegativeButton("Back") { _, _ -> instDialog(lastInst) }
        when (choosenInst) {
            "Hints" -> {
                builder.setMessage(R.string.hints)
            }
            "Info" -> {
                builder.setMessage(R.string.information)
            }
            "Flags" -> {
                builder.setMessage(R.string.flagsButton)
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

    private fun setup() {
        val sharedPreferences = getSharedPreferences("appInfo", Context.MODE_PRIVATE)
        val st = sharedPreferences.getInt("stateKey", 0)
        if (st == 0) {
            set()
        }
    }

    private fun set() {
        val st = (0..5).random()
        val sharedPreferences = getSharedPreferences("appInfo", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("stateKey", st)
        editor.apply()
        editor.commit()
    }


}