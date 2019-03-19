package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.paulb.whale2fa.tfaEasy
import com.example.paulb.whale2fa.tfaKiller
import com.example.paulb.whale2fa.tfaMedium
import kotlinx.android.synthetic.main.activity_difficulty_selector.*

class DifficultySelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty_selector)
        val selector = intent.getIntExtra("vulnerability",0)
        VulnText.text = intent.getStringExtra("name")
        killerWhale.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, sqli_hard::class.java)
                3 -> intent = Intent(this, tfaKiller::class.java)
                5 -> intent = Intent(this, PoorAuthentication::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",3)
            println(selector)
            startActivity(intent)
        }
        whaleMed.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, sqli_medium::class.java)
                3 -> intent = Intent(this, tfaMedium::class.java)
                5 -> intent = Intent(this, PoorAuthenticationMed::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",2)
            println(selector)
            startActivity(intent)
        }
        whaleasy.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, sqli_easy::class.java)
                3 -> intent = Intent(this, tfaEasy::class.java)
                5 -> intent = Intent(this, PoorAuthentication::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",1)
            println(selector)
            startActivity(intent)
        }
        mtgtBtn.setOnClickListener{
            val builder = android.support.v7.app.AlertDialog.Builder(this, R.style.whaleDialog)//, R.style.mitigationDialogueTheme)
            // Set the alert dialog title
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> {builder.setTitle("SQL Injection Mitigations"); builder.setMessage(R.string.SQLiMitigations)}
                3 -> {builder.setTitle("2FA Mitigations"); builder.setMessage(R.string.tfamitigations)}
                5 -> {builder.setTitle("Poor Authentication"); builder.setMessage(R.string.pamitigations)}
                6 -> intent = Intent(this, InsecureLogging::class.java)
                else -> println(selector)
            }
            val dialog: android.support.v7.app.AlertDialog = builder.create()
            dialog.show()
        }

        //Go to progress page
        progBtn.setOnClickListener{
            startActivity(Intent(this, ProgressPage::class.java))
        }

        //Call information dialog creation
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        BackBtn.setOnClickListener{
            onBackPressed()
        }

    }

    private fun informationDialog(){
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Difficulty Selection")
        builder.setMessage("Here you can select the difficulty of your chosen vulnerability to exploit - easy, medium, or 'killer'. \nYou can also view how your chosen vulnerability can be mitigated, or go to the progress page and update your progress by entering your captured flags.")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

