package whalethedvaa.whalethedvaa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_the_whala.*


class TheWhala : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_whala)


        GetSplashing.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }
    }
}