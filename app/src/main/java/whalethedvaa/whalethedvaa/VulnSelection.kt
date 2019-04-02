package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_vuln_selection2.*

class VulnSelection : AppCompatActivity() {

    var foundFlags: String = ""
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
        foundFlags = mProg.getString("found", "")
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
//        }
//        if(foundFlags.contains("B055M4N") && foundFlags.contains("N1C3-W4N") && foundFlags.contains("4M4Z1NG")){
//            Vuln2.setBackgroundResource(R.drawable.fin1)
//        } else if(foundFlags.contains("N1C3-W4N") && foundFlags.contains("4M4Z1NG")){
//            Vuln2.setBackgroundResource(R.drawable.b23)
//        }else if(foundFlags.contains("B055M4N") && foundFlags.contains("4M4Z1NG")){
//            Vuln2.setBackgroundResource(R.drawable.b13)
//        }else if(foundFlags.contains("B055M4N") && foundFlags.contains("N1C3-W4N")){
//            Vuln2.setBackgroundResource(R.drawable.b12)
//        }else if(foundFlags.contains("B055M4N")){
//            Vuln2.setBackgroundResource(R.drawable.b1)
//        }else if(foundFlags.contains("N1C3-W4N")){
//            Vuln2.setBackgroundResource(R.drawable.b2)
//        }else if(foundFlags.contains("4M4Z1NG")){
//            Vuln2.setBackgroundResource(R.drawable.b3)
//        }else {
//            Vuln2.setBackgroundResource(R.drawable.b0)
//        }
//        if(foundFlags.contains("P0K3RF4C3") && foundFlags.contains("H0N3YB33") && foundFlags.contains("C4SC4D1NG")){
//            Vuln3.setBackgroundResource(R.drawable.fin1)
//        } else if(foundFlags.contains("H0N3YB33") && foundFlags.contains("C4SC4D1NG")){
//            Vuln3.setBackgroundResource(R.drawable.b23)
//        }else if(foundFlags.contains("P0K3RF4C3") && foundFlags.contains("C4SC4D1NG")){
//            Vuln3.setBackgroundResource(R.drawable.b13)
//        }else if(foundFlags.contains("P0K3RF4C3") && foundFlags.contains("H0N3YB33")){
//            Vuln3.setBackgroundResource(R.drawable.b12)
//        }else if(foundFlags.contains("P0K3RF4C3")){
//            Vuln3.setBackgroundResource(R.drawable.b1)
//        }else if(foundFlags.contains("H0N3YB33")){
//            Vuln3.setBackgroundResource(R.drawable.b2)
//        }else if(foundFlags.contains("C4SC4D1NG")){
//            Vuln3.setBackgroundResource(R.drawable.b3)
//        }else {
//            Vuln3.setBackgroundResource(R.drawable.b0)
//        }
//        if(foundFlags.contains("D1V3T34M") && foundFlags.contains("F1SHF00D") && foundFlags.contains("T4STY-K3LP")){
//            Vuln4.setBackgroundResource(R.drawable.fin1)
//        } else if(foundFlags.contains("F1SHF00D") && foundFlags.contains("T4STY-K3LP")){
//            Vuln4.setBackgroundResource(R.drawable.b23)
//        }else if(foundFlags.contains("D1V3T34M") && foundFlags.contains("T4STY-K3LP")){
//            Vuln4.setBackgroundResource(R.drawable.b13)
//        }else if(foundFlags.contains("D1V3T34M") && foundFlags.contains("F1SHF00D")){
//            Vuln4.setBackgroundResource(R.drawable.b12)
//        }else if(foundFlags.contains("D1V3T34M")){
//            Vuln4.setBackgroundResource(R.drawable.b1)
//        }else if(foundFlags.contains("F1SHF00D")){
//            Vuln4.setBackgroundResource(R.drawable.b2)
//        }else if(foundFlags.contains("T4STY-K3LP")){
//            Vuln4.setBackgroundResource(R.drawable.b3)
//        }else {
//            Vuln4.setBackgroundResource(R.drawable.b0)
//        }
//        if(foundFlags.contains("H3RM4N-M3LV1LL3") && foundFlags.contains("C0R4L-R33F") && foundFlags.contains("SP0NG3B0B")){
//            Vuln1.setBackgroundResource(R.drawable.fin1)
//        } else if(foundFlags.contains("C0R4L-R33F") && foundFlags.contains("SP0NG3B0B")){
//            Vuln1.setBackgroundResource(R.drawable.b23)
//        }else if(foundFlags.contains("H3RM4N-M3LV1LL3") && foundFlags.contains("SP0NG3B0B")){
//            Vuln1.setBackgroundResource(R.drawable.b13)
//        }else if(foundFlags.contains("H3RM4N-M3LV1LL3") && foundFlags.contains("C0R4L-R33F")){
//            Vuln1.setBackgroundResource(R.drawable.b12)
//        }else if(foundFlags.contains("H3RM4N-M3LV1LL3")){
//            Vuln1.setBackgroundResource(R.drawable.b1)
//        }else if(foundFlags.contains("C0R4L-R33F")){
//            Vuln1.setBackgroundResource(R.drawable.b2)
//        }else if(foundFlags.contains("SP0NG3B0B")){
//            Vuln1.setBackgroundResource(R.drawable.b3)
//        }else {
//            Vuln1.setBackgroundResource(R.drawable.b0)
//        }
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
