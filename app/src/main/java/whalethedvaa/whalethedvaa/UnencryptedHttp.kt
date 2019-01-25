package whalethedvaa.whalethedvaa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_unencrypted_http.*

class UnencryptedHttp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unencrypted_http)
        val level = intent.getIntExtra("Level",0) //level is the difficulty setting 1 easy 2 medium and 3 hard
        println(level) //comment out, debug for level variable



        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }
    }
}
