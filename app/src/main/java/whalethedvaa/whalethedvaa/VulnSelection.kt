package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_vuln_selection2.*

class VulnSelection : AppCompatActivity() {

    var totalFlags: String = "" //Initialise string to store all entered flags

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vuln_selection2)

        //Call information dialog creation
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        BackBtn.setOnClickListener{
            onBackPressed()
            onStop()
        }

        ViewProgression.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
            onStop()
        }

        Vuln1.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",1)
            intent.putExtra("name",Vuln1.text)
            onStop()
            startActivity(intent)
        }

        Vuln2.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",2)
            intent.putExtra("name",Vuln2.text)
            onStop()
            startActivity(intent)
        }

        Vuln3.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",3)
            intent.putExtra("name",Vuln3.text)
            onStop()
            startActivity(intent)
        }

        Vuln4.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",4)
            intent.putExtra("name",Vuln4.text)
            onStop()
            startActivity(intent)
        }

        Vuln5.setOnClickListener {
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability", 5)
            intent.putExtra("name", Vuln5.text)
            onStop()
            startActivity(intent)
        }
        //opens further reading page
        furtherReading.setOnClickListener {
            val intent = Intent(this, FurtherReading::class.java)
            onStop()
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val mProg = getSharedPreferences("progress", 0)
        totalFlags = mProg.getString("level", "0,0,0,0,0")
        //checkProgress()
        setButtons()
    }

    fun setButtons() {
        var level = totalFlags.split(",").toTypedArray()

        for(i in 1..5){
            val btn = "Vuln" + i
            var btns = resources.getIdentifier(btn, "id", this.packageName)
            val bttns : Button = findViewById(btns)
            when(level[i-1]){
                "0" -> {bttns.setBackgroundResource(R.drawable.b0)}
                "1" -> {bttns.setBackgroundResource(R.drawable.b3)}
                "2" -> {bttns.setBackgroundResource(R.drawable.b2)}
                "3" -> {bttns.setBackgroundResource(R.drawable.b1)}
                "4" -> {bttns.setBackgroundResource(R.drawable.b12)}
                "5" -> {bttns.setBackgroundResource(R.drawable.b13)}
                "6" -> {bttns.setBackgroundResource(R.drawable.b23)}
                "7" -> {bttns.setBackgroundResource(R.drawable.b123)}
                else -> println(i)
            }
       }
 }



    private fun informationDialog(){
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Vulnerability Information")
        builder.setMessage(R.string.VulnSelectorInfo)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
