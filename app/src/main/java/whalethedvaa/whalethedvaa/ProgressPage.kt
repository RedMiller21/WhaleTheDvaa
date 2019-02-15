package whalethedvaa.whalethedvaa

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_progress_page.*

class ProgressPage : AppCompatActivity() {
    var easyFlags = arrayOf ("p0k3rf4c3", "d1v3t34m")
    var medFlags = arrayOf ("h0n3yb33")
    var killerFlags = arrayOf ("c4sc4d1ng")
    var easyFound : Int = 0
    var medFound : Int = 0
    var killerFound : Int = 0
    var totalFound : Int = 0
    var foundFlags = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_page)

        txtTotal.text = "Total - $totalFound/18"
        txtEasy.text = "Easy - $easyFound/6"
        txtMed.text = "Medium - $medFound/6"
        txtKiller.text = "Killer - $killerFound/6"

        totalEasy.progress = (easyFound/6)*100
        totalMedium.progress = (medFound/6)*100
        totalKiller.progress = (killerFound/6)*100
        totalProgress.progress = (totalFound/18)*100

        //totalEasy.progressDrawable.setColorFilter(Color.rgb(112,173,71), PorterDuff.Mode.SRC_IN)
        /*totalEasy.color(Color.rgb(112,173,71), PorterDuff.Mode.SRC_IN)
        totalMedium.progressDrawable.setColorFilter(Color.rgb(255,192,0), PorterDuff.Mode.SRC_IN)
        totalKiller.progressDrawable.setColorFilter(Color.rgb(238,54,50), PorterDuff.Mode.SRC_IN)
        totalProgress.progressDrawable.setColorFilter(Color.rgb(68,114,196), PorterDuff.Mode.SRC_IN)*/

        //Call information dialog creation
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener{
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

        EnterFlagBtn.setOnClickListener{
            enterFlag()
        }
    }
    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Progression Information")
        builder.setMessage("Example Information")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun enterFlag(){

        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.flagdialog, null)
        val builder = AlertDialog.Builder(this)

        val userInput = promptsView.findViewById(R.id.EnterFlagText) as EditText

        // set flagdialog.xml to flagdialog builder
        builder.setView(promptsView)
            .setCancelable(false)
            .setPositiveButton(R.string.enter){ _, _ ->
                updateProgress(userInput.text.toString())
            }
            .setNegativeButton(R.string.cancel){ dialog, _ -> dialog.cancel() }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun updateProgress(flag: String){
        //update
        println(flag)
        Toast.makeText(this, flag, Toast.LENGTH_SHORT).show()
        var flagFound : Boolean = true
        when {
            foundFlags.contains(flag) -> {Toast.makeText(this, "This flag has already been entered", Toast.LENGTH_SHORT).show(); return}
            easyFlags.contains(flag) -> {easyFound++; txtEasy.text = "Easy - $easyFound/6"; totalEasy.progress = easyFound*100/6; foundFlags += flag}
            medFlags.contains(flag) -> {medFound++; txtMed.text = "Medium - $medFound/6"; totalMedium.progress = medFound*100/6; foundFlags += flag}
            killerFlags.contains(flag) -> {killerFound++; txtKiller.text = "Killer - $killerFound/6"; totalKiller.progress = killerFound*100/6; foundFlags += flag}
            else -> {flagFound = false; Toast.makeText(this, "Error: flag not found", Toast.LENGTH_SHORT).show(); return}
        }
        if(flagFound){
            totalFound++
            txtTotal.text = "Total - $totalFound/18"
            totalProgress.progress = totalFound*(100/18)
        }
    }
}