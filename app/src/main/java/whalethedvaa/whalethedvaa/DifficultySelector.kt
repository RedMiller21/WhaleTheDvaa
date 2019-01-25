package whalethedvaa.whalethedvaa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_difficulty_selector.*

class DifficultySelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty_selector);
        val selector = intent.getIntExtra("vulnerability",0)
        KillerWhale.setOnClickListener{
            when(selector) {
                1 -> intent = Intent(this, HardCoding::class.java)
                2 -> intent = Intent(this, SQLI::class.java)
                3 -> intent = Intent(this, ManInTheMiddle::class.java)
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, UnencryptedHttp::class.java)
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
                3 -> intent = Intent(this, ManInTheMiddle::class.java)
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, UnencryptedHttp::class.java)
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
                3 -> intent = Intent(this, ManInTheMiddle::class.java)
                4 -> intent = Intent(this, InsecureTokens::class.java)
                5 -> intent = Intent(this, UnencryptedHttp::class.java)
                6 -> intent = Intent(this, InsecureLogging::class.java)
                7 -> intent = Intent(this, InsecureDataStorage::class.java)
                else -> println(selector)
            }
            intent.putExtra("Level",1)
            println(selector)
            startActivity(intent)
        }

        BackBtn.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

    }}

