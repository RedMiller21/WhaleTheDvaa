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
        setContentView(R.layout.activity_difficulty_selector);
        val selector = intent.getIntExtra("vulnerability",0)
        VulnText.text = intent.getStringExtra("name")
        KillerWhale.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, SQLI::class.java)
                3 -> intent = Intent(this, tfaKiller::class.java)
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, PoorAuthentication::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                7 -> intent = Intent(this, InsecureDataStorage::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",3)
            println(selector)
            startActivity(intent)
        }
        WhaleMed.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, SQLI::class.java)
                3 -> intent = Intent(this, tfaMedium::class.java)
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, PoorAuthentication::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                7 -> intent = Intent(this, InsecureDataStorage::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",2)
            println(selector)
            startActivity(intent)
        }
        whaleasy.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, SQLI::class.java)
                3 -> intent = Intent(this, tfaEasy::class.java)
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, PoorAuthentication::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                7 -> intent = Intent(this, InsecureDataStorage::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",1)
            println(selector)
            startActivity(intent)
        }
        mtgtBtn.setOnClickListener{
            val builder = android.support.v7.app.AlertDialog.Builder(this)
            // Set the alert dialog title
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, SQLI::class.java)
                3 -> {builder.setTitle("2FA Mitigations"); builder.setMessage("These tokens should be completely randomised and should not follow any distinguishable pattern. The use of a time-based randomisation methods or algorithms to calculate the next code makes it easy to predict what the next code will be, using the current time. Instead, a randomisation function should be used to create a unique token not based on any pattern or other value as to avoid repetition or the chances of this token falling into the wrong hands and being used by an attacker to access a user's account.")}
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, PoorAuthentication::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                7 -> intent = Intent(this, InsecureDataStorage::class.java)
                else -> println(selector)
            }
            val dialog: android.support.v7.app.AlertDialog = builder.create()
            dialog.show()
           // intent.putExtra("Level",2)
           // println(selector)
           // startActivity(intent)
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
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

    }

    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Difficulty Selection")
        builder.setMessage("Here you can select the difficulty of your chosen vulnerability to exploit - easy, medium, or 'killer'. \nYou can also view how your chosen vulnerability can be mitigated, or go to the progress page and update your progress by entering your captured flags.")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

