package whalethedvaa.whalethedvaa

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_further_reading.*

class FurtherReading : AppCompatActivity(){

    /**
     * allows user to select a vulnerability and passes a number to the dialogue function
     */
     override fun onCreate(savedInstanceState: Bundle?){
         super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_further_reading)

        //var selector = 0
           btn2FA.setOnClickListener{
               //selector = 1
               readingDialogue(1)
           }

           btnHardCoding.setOnClickListener{
               //selector = 2
                readingDialogue(2)
           }

           btnInsecureLogging.setOnClickListener{
               //selector = 3
               readingDialogue(3)
           }

           btnPoorAuth.setOnClickListener{
               //selector = 4
               readingDialogue(4)
           }

           btnSQLi.setOnClickListener{
               //selector = 5
               readingDialogue(5)
           }

   }

    /**
     * displays a dialogue containing further reading for each vulnerability, with further reading being stored in string file
     */
    private fun readingDialogue(selector: Int){
        //declare the builder for the dialogue
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)

        when(selector) {
            1 -> {builder.setTitle("Two Factor Authentication - Further Reading"); builder.setMessage(R.string.TwoFAFurtherReading)}
            2 -> {builder.setTitle("Hardcoded Vulnerabilities - Further Reading"); builder.setMessage(R.string.HardcodingFurtherReading)}
            3 -> {builder.setTitle("Insecure Logging - Further Reading"); builder.setMessage(R.string.InsecureLoggingFurtherReading)}
            4 -> {builder.setTitle("Poor Authentication - Further Reading"); builder.setMessage(R.string.PoorAuthFurtherReading)}
            5 -> {builder.setTitle("SQL Injection - Further Reading"); builder.setMessage(R.string.SQLiFurtherReading)}
        else -> println(selector)
        }
    }
}
