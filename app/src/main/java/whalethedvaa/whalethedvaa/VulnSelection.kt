package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vuln_selection2.*

class VulnSelection : AppCompatActivity() {

    var foundFlags : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vuln_selection2)

        //Call information dialog creation
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        BackBtn.setOnClickListener{
            onBackPressed()
        }

        val mProg = getSharedPreferences("progress", 0)
        foundFlags = mProg.getString("found", "")
        //checkProgress()

        ViewProgression.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }

        furtherReading.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)

        }

        Vuln1.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",1)
            intent.putExtra("name",Vuln1.text)
            startActivity(intent)
        }

        Vuln3.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",3)
            intent.putExtra("name",Vuln3.text)
            startActivity(intent)
        }

        Vuln5.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",5)
            intent.putExtra("name",Vuln5.text)
            startActivity(intent)
        }

        Vuln6.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",6)
            intent.putExtra("name",Vuln6.text)
            startActivity(intent)
        }
        //opens further reading page
        furtherReading.setOnClickListener {
            val intent = Intent(this, FurtherReading::class.java)
            startActivity(intent)
        }
    }

    fun setButtons() {


        val flag  = arrayOf("P0K3RF4C3", "D1V3T34M", "B055M4N","F1NNDOG","C4SC4D1NG", "4M4Z1NG", "T4STY-K3lP","H0N3YB33", "N1C3-W4N", "F1SHF00D", "C4PTB1RD23Y3")
        if(foundFlags.contains("F1NNDOG") && foundFlags.contains("C4PTB1RD23Y3") && foundFlags.contains("WH413L0RD")){
            Vuln5.setBackgroundResource(R.drawable.fin1)
        } else if(foundFlags.contains("C4PTB1RD23Y3") && foundFlags.contains("WH413L0RD")){
            Vuln5.setBackgroundResource(R.drawable.b23)
        }else if(foundFlags.contains("F1NNDOG") && foundFlags.contains("WH413L0RD")){
            Vuln5.setBackgroundResource(R.drawable.b13)
        }else if(foundFlags.contains("F1NNDOG") && foundFlags.contains("C4PTB1RD23Y3")){
            Vuln5.setBackgroundResource(R.drawable.b12)
        }else if(foundFlags.contains("F1NNDOG")){
            Vuln5.setBackgroundResource(R.drawable.b1)
        }else if(foundFlags.contains("C4PTB1RD23Y3")){
            Vuln5.setBackgroundResource(R.drawable.b2)
        }else if(foundFlags.contains("WH413L0RD")){
            Vuln5.setBackgroundResource(R.drawable.b3)
        }else {
            Vuln5.setBackgroundResource(R.drawable.b0)
        }

//        if(foundFlags.contains("F1NNDOG") && foundFlags.contains("C4PTB1RD23Y3") && foundFlags.contains("WH413L0RD")){
//            Vuln5.setBackgroundResource(R.drawable.fin1)
//        } else if(foundFlags.contains("C4PTB1RD23Y3") && foundFlags.contains("WH413L0RD")){
//            Vuln5.setBackgroundResource(R.drawable.b23)
//        }else if(foundFlags.contains("F1NNDOG") && foundFlags.contains("WH413L0RD")){
//            Vuln5.setBackgroundResource(R.drawable.b13)
//        }else if(foundFlags.contains("F1NNDOG") && foundFlags.contains("C4PTB1RD23Y3")){
//            Vuln5.setBackgroundResource(R.drawable.b12)
//        }else if(foundFlags.contains("F1NNDOG")){
//            Vuln5.setBackgroundResource(R.drawable.b1)
//        }else if(foundFlags.contains("C4PTB1RD23Y3")){
//            Vuln5.setBackgroundResource(R.drawable.b2)
//        }else if(foundFlags.contains("WH413L0RD")){
//            Vuln5.setBackgroundResource(R.drawable.b3)
//        }else {
//            Vuln5.setBackgroundResource(R.drawable.b0)
//            println("PA: " + foundFlags)
      //  }


//        when{
//            foundFlags.contains("p0k3rf4c3")-> Vuln5.setBackgroundResource(R.drawable.b1)
//            foundFlags.contains("h0n3yb33")-> Vuln5.setBackgroundResource(R.drawable.b2)
//            foundFlags.contains("c4sc4d1ng")-> Vuln5.setBackgroundResource(R.drawable.b3)
//            foundFlags.contains("p0k3rf4c3") && foundFlags.contains("h0n3yb33")-> Vuln5.setBackgroundResource(R.drawable.b12)
//            foundFlags.contains("p0k3rf4c3") && foundFlags.contains("c4sc4d1ng")-> Vuln5.setBackgroundResource(R.drawable.b13)
//            foundFlags.contains("h0n3yb33") && foundFlags.contains("c4sc4d1ng")-> Vuln5.setBackgroundResource(R.drawable.b23)
//            foundFlags.contains("p0k3rf4c3") && foundFlags.contains("h0n3yb33") && foundFlags.contains("c4sc4d1ng")-> Vuln5.setBackgroundResource(R.drawable.fin1)
//            else -> {println("2FA"); return}
//        }
    }


    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Vulnerability Information")
        builder.setMessage(R.string.VulnSelectorInfo)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}