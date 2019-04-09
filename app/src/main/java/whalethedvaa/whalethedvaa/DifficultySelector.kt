package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.paulb.whale2fa.tfaEasy
import com.example.paulb.whale2fa.tfaKiller
import com.example.paulb.whale2fa.tfaMedium
import kotlinx.android.synthetic.main.activity_difficulty_selector.*

class DifficultySelector : AppCompatActivity() {
    var totalFlags: String = "" //Initialise string to store all entered flags
    private var selector : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty_selector)
        selector = intent.getIntExtra("vulnerability", 0)
        VulnText.text = intent.getStringExtra("name")

        if (getIntent().getBooleanExtra("crash", false)) {
            Toast.makeText(this, "App restarted after crash", Toast.LENGTH_LONG).show();
        }

        val mProg = getSharedPreferences("progress", 0) //Load flags found previously by user
        totalFlags = mProg.getString("level", "0,0,0,0,0")
        updateBtns(selector)
        killerWhale.setOnClickListener {
            when (selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, sqli_hard::class.java)
                3 -> intent = Intent(this, tfaKiller::class.java)
                4 -> intent = Intent(this, PoorAuthenticationKiller::class.java)
                5 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level", 3)
            println(selector)
            onStop()
            startActivity(intent)
        }
        whaleMed.setOnClickListener {
            when (selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, sqli_medium::class.java)
                3 -> intent = Intent(this, tfaMedium::class.java)
                4 -> intent = Intent(this, PoorAuthenticationMed::class.java)
                5 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level", 2)
            println(selector)
            onStop()
            startActivity(intent)
        }
        whaleasy.setOnClickListener {
            when (selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, sqli_easy::class.java)
                3 -> intent = Intent(this, tfaEasy::class.java)
                4 -> intent = Intent(this, PoorAuthentication::class.java)
                5 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level", 1)
            println(selector)
            onStop()
            startActivity(intent)
        }
        mtgtBtn.setOnClickListener {
            val builder = android.support.v7.app.AlertDialog.Builder(
                this,
                R.style.whaleDialog
            )//, R.style.mitigationDialogueTheme)
            // Set the alert dialog title
            when (selector) {
                1 -> {
                    builder.setTitle("Hardcoding Mitigations"); builder.setMessage(R.string.HardcodingMitigations)
                }
                2 -> {
                    builder.setTitle("SQL Injection Mitigations"); builder.setMessage(R.string.SQLiMitigations)
                }
                3 -> {
                    builder.setTitle("2FA Mitigations"); builder.setMessage(R.string.tfamitigations)
                }
                4 -> {
                    builder.setTitle("Poor Authentication Mitigations"); builder.setMessage(R.string.pamitigations)
                }
                5 -> {
                    builder.setTitle("Insecure Logging Mitigations"); builder.setMessage(R.string.InsecureLogsMitigations)
                }
                else -> println(selector)
            }
            val dialog: android.support.v7.app.AlertDialog = builder.create()
            dialog.show()
        }

        //Go to progress page
        progBtn.setOnClickListener {
            startActivity(Intent(this, ProgressPage::class.java))
        }

        //Call information dialog creation
        InformationBtn.setOnClickListener {
            informationDialog()
        }

        BackBtn.setOnClickListener {
            onBackPressed()
        }

    }

    private fun updateBtns(selector: Int) {
        var level = totalFlags.split(",").toTypedArray()
        when(level[selector-1]){
            "0" -> {whaleasy.setImageResource(R.drawable.whaleasy)
            whaleMed.setImageResource(R.drawable.whalemedi)
            killerWhale.setImageResource(R.drawable.killerwhale)
            }
            "1" -> {killerWhale.setImageResource(R.drawable.kil2)}
            "2" -> {whaleMed.setImageResource(R.drawable.med2)}
            "3" -> {whaleasy.setImageResource(R.drawable.low2)}
            "4" -> {whaleasy.setImageResource(R.drawable.low2)
                whaleMed.setImageResource(R.drawable.med2)}
            "5" -> {whaleasy.setImageResource(R.drawable.low2)
                killerWhale.setImageResource(R.drawable.kil2)}
            "6" -> {whaleMed.setImageResource(R.drawable.med2)
                killerWhale.setImageResource(R.drawable.kil2)}
            "7" -> {whaleasy.setImageResource(R.drawable.low2)
                whaleMed.setImageResource(R.drawable.med2)
                killerWhale.setImageResource(R.drawable.kil2)}
        }
    }

    private fun informationDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Difficulty Selection")
        builder.setMessage("Here you can select the difficulty of your chosen vulnerability to exploit - easy, medium, or 'killer'. \nYou can also view how your chosen vulnerability can be mitigated, or go to the progress page and update your progress by entering your captured flags.")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onStart(){
        super.onStart()
        val mProg = getSharedPreferences("progress", 0) //Load flags found previously by user
        totalFlags = mProg.getString("level", "0,0,0,0,0")
        updateBtns(selector)
    }
}

