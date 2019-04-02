package whalethedvaa.whalethedvaa

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_further_reading.*
import android.text.method.LinkMovementMethod
import android.widget.TextView



class FurtherReading : AppCompatActivity(){

    /**
     * allows user to select a vulnerability and passes a number to the dialogue function
     */
     override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_further_reading)
           btn2FA.setOnClickListener{
               readingDialogue(1)
           }

           btnHardCoding.setOnClickListener{
                readingDialogue(2)
           }

           btnInsecureLogging.setOnClickListener{
               readingDialogue(3)
           }

           btnPoorAuth.setOnClickListener{
               readingDialogue(4)
           }

           btnSQLi.setOnClickListener{
               readingDialogue(5)
           }

   }

    /**
     * displays a dialogue containing further reading for each vulnerability, with further reading being stored in string file
     */
    private fun readingDialogue(selector: Int) {
        //declare the builder for the dialogue
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        val message = TextView(this)

        message.movementMethod = LinkMovementMethod.getInstance()

        when(selector) {
            1 -> {builder.setTitle("Two Factor Authentication - Further Reading");  message.setText(R.string.TwoFAFurtherReading)}
            2 -> {builder.setTitle("Hardcoded Vulnerabilities - Further Reading"); message.setText(R.string.HardcodingFurtherReading)}
            3 -> {builder.setTitle("Insecure Logging - Further Reading"); message.setText(R.string.InsecureLoggingFurtherReading)}
            4 -> {builder.setTitle("Poor Authentication - Further Reading"); message.setText(R.string.PoorAuthFurtherReading)}
            5 -> {builder.setTitle("SQL Injection - Further Reading"); message.setText(R.string.SQLiFurtherReading)}
        else -> println(selector)
        }
        builder.setView(message)
        message.textSize = 16F
        message.setTextColor(Color.parseColor("#222A35"))
        message.setPadding(40, 20, 40, 20)
        builder.show()
    }
}
