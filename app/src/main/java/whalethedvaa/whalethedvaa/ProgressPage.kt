package whalethedvaa.whalethedvaa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_progress_page.*

class ProgressPage : AppCompatActivity() {
    var easyFlags = arrayOf("p0k3rf4c3")
    var medFlags = arrayOf("h0n3yb33")
    var killerFlags = arrayOf("c4sc4d1ng")
    var easyFound: Int = 0
    var medFound: Int = 0
    var killerFound: Int = 0
    var totalFound: Int = 0
    var foundFlags : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mProg = getSharedPreferences("progress", 0)
        easyFound = mProg.getInt("easy", 0)
        medFound = mProg.getInt("med", 0)
        killerFound = mProg.getInt("killer", 0)
        totalFound = mProg.getInt("total", 0)
        foundFlags = mProg.getString("found", "")

        setContentView(R.layout.activity_progress_page)

        //Update progress bars and displays
        updateForm()

        //Call information dialog creation
        InformationBtn.setOnClickListener {
            informationDialog()
        }

        //Reset progress
        ResetBtn.setOnClickListener {
            resetProg()
        }


        //Back button will move back to the vulnerability selection activity
        BackBtn.setOnClickListener {
            //When back button pressed
            //Add flag variables to shared preferences
            val mEditor = mProg.edit()
            mEditor.putInt("easy", easyFound).commit()
            mEditor.putInt("med", medFound).commit()
            mEditor.putInt("killer", killerFound).commit()
            mEditor.putInt("total", totalFound).commit()
            mEditor.putString("found", foundFlags).commit()

            //Go back
            val intent = Intent(this, VulnSelection::class.java)
            startActivity(intent)
        }

        EnterFlagBtn.setOnClickListener {
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

    private fun resetProg(){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
            .setTitle("Are you sure you want to reset your progress?")
            .setPositiveButton("Yes"){ _, _ ->
                //reset flag variables
                easyFound = 0
                medFound = 0
                killerFound = 0
                totalFound = 0
                foundFlags = ""
                //update bars and form
                updateForm()
            }
            .setNegativeButton("No"){ dialog, _ -> dialog.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun updateProgress(flag: String){
        //update
        var flagFound : Boolean = true
        when {
            foundFlags.contains(flag) -> {errorMsg(1); return}
            easyFlags.contains(flag) -> {easyFound++; txtEasy.text = "Easy - $easyFound/6"; totalEasy.progress = easyFound*100/6; foundFlags += flag}
            medFlags.contains(flag) -> {medFound++; txtMed.text = "Medium - $medFound/6"; totalMedium.progress = medFound*100/6; foundFlags += flag}
            killerFlags.contains(flag) -> {killerFound++; txtKiller.text = "Killer - $killerFound/6"; totalKiller.progress = killerFound*100/6; foundFlags += flag}
            else -> {errorMsg(0); return}
        }
        if(flagFound){
            totalFound++
            txtTotal.text = "Total - $totalFound/18"
            totalProgress.progress = totalFound*(100/18)
        }
    }

    private fun updateForm(){
        txtTotal.text = "Total - $totalFound/18"
        txtEasy.text = "Easy - $easyFound/6"
        txtMed.text = "Medium - $medFound/6"
        txtKiller.text = "Killer - $killerFound/6"

        totalEasy.progress = (easyFound*100)/6
        totalMedium.progress = (medFound*100)/6
        totalKiller.progress = (killerFound*100)/6
        totalProgress.progress = (totalFound*100)/18
    }

    private fun errorMsg(case : Int){
        var title : String = ""
        when (case){
            0 -> title = "Error: flag not found"
            1 -> title = "This flag has already been entered"
        }
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
            .setTitle(title)
            .setNegativeButton("Okay"){ dialog, _ -> dialog.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}