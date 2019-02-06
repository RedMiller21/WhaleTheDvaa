package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener{
            val intent = Intent(this, TheWhala::class.java)
            startActivity(intent)
        }
    }
}
