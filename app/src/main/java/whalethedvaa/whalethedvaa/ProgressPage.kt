package whalethedvaa.whalethedvaa

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_progress_page.*

class ProgressPage : AppCompatActivity() {
    var easyFlags = arrayOf("P0K3RF4C3", "D1V3T34M", "4M4Z1NG","F1NNDOG", "H3RM4N-M3LV1LL3") //Array of flags for easy vulns
    var medFlags = arrayOf("H0N3YB33", "N1C3-W4N", "F1SHF00D", "C4PTB1RD23Y3", "C0R4L-R33F") //Flags for medium
    var killerFlags = arrayOf("C4SC4D1NG", "B055M4N", "T4STY-K3LP", "WH413L0RD", "SP0NG3B0B") //Flags for killer
    var easyFound: Int = 0 //Initialise var for number of easy flags found
    var medFound: Int = 0 //medium flags found
    var killerFound: Int = 0 //killer flags found
    var totalFound: Int = 0 //total number of flags found
    var foundFlags: String? = "" //Initialise string to store all entered flags
    var totalFlags: String = "" //Initialise string to store all entered flags

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mProg = getSharedPreferences("progress", 0) //Load flags found previously by user
        easyFound = mProg.getInt("easy", 0)
        medFound = mProg.getInt("med", 0)
        killerFound = mProg.getInt("killer", 0)
        totalFound = mProg.getInt("total", 0)
        foundFlags  = mProg.getString("found", "")
        totalFlags = mProg.getString("level", "0,0,0,0,0")
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
            mEditor.putInt("easy", easyFound).apply()
            mEditor.putInt("med", medFound).apply()
            mEditor.putInt("killer", killerFound).apply()
            mEditor.putInt("total", totalFound).apply()
            mEditor.putString("found", foundFlags).apply()
            mEditor.putString("level", totalFlags).apply()

            //Go back
            onBackPressed()
        }

        EnterFlagBtn.setOnClickListener {
            //Button to enter flags
            enterFlag() //Call function for entering flag
        }
    }

    private fun informationDialog() {
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        // Set the alert dialog title
        builder.setTitle("Progression Information")
        builder.setMessage("Example Information")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun enterFlag() {

        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.flagdialog, null)
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)

        val userInput = promptsView.findViewById(R.id.EnterFlagText) as EditText

        // set flagdialog.xml to flagdialog builder
        builder.setView(promptsView)
            .setCancelable(false)
            .setPositiveButton(R.string.enter) { _, _ ->
                updateProgress(userInput.text.toString())
            }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun resetProg() { //Function to reset progress by clearing flags found
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        builder.setCancelable(false)
            .setTitle("Are you sure you want to reset your progress?")
            .setPositiveButton("Yes") { _, _ ->
                //If user confirms that they want to reset
                //reset flag variables
                easyFound = 0
                medFound = 0
                killerFound = 0
                totalFound = 0
                foundFlags = ""
                //update bars and form
                updateFlags()
                updateForm()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.cancel() } //Else, return to app
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun updateProgress(flag: String) { //Update progress after user has entered flag
        //update
        when { //Case statement for entered flag.
            foundFlags!!.contains(flag) -> {
                errorMsg(1); return
            } //If flag has already been entered, show error message.
            //If flag exists, increment number of found flags of that difficulty, update progress bars and displays on form,
            easyFlags.contains(flag) -> {
                easyFound++; txtEasy.text = "Easy - $easyFound/5"; totalEasy.progress = easyFound * 100 / 5
            }
            medFlags.contains(flag) -> {
                medFound++; txtMed.text = "Medium - $medFound/5"; totalMedium.progress = medFound * 100 / 5
            }
            killerFlags.contains(flag) -> {
                killerFound++; txtKiller.text = "Killer - $killerFound/5"; totalKiller.progress = killerFound * 100 / 5
            }
            else -> {
                errorMsg(0); return
            } //If flag does not exist, show error message.
        }
        foundFlags += flag //add flag to string list of found flags
        updateFlags()
        totalFound++ //increment number of flags found by 1
        txtTotal.text = "Total - $totalFound/15" //update form for total number of flags found
        totalProgress.progress = (totalFound * 100) / 15 //update progress bar for total flags
        if (totalFound == 15) { //If all 15 flags have been found
            complete() //call on-completion function
        }
    }

   private fun updateFlags() {

        var level = totalFlags.split(",").toTypedArray()
        //Poor Authentication flags
        if (foundFlags!!.contains("F1NNDOG") && foundFlags!!.contains("C4PTB1RD23Y3") && foundFlags!!.contains("WH413L0RD")) {
            level[3] = "7,"
        } else if (foundFlags!!.contains("C4PTB1RD23Y3") && foundFlags!!.contains("WH413L0RD")) {
            level[3] = "6,"
        } else if (foundFlags!!.contains("F1NNDOG") && foundFlags!!.contains("WH413L0RD")) {
            level[3] = "5,"
        } else if (foundFlags!!.contains("F1NNDOG") && foundFlags!!.contains("C4PTB1RD23Y3")) {
            level[3] = "4,"
        } else if (foundFlags!!.contains("F1NNDOG")) {
            level[3] = "3,"
        } else if (foundFlags!!.contains("C4PTB1RD23Y3")) {
            level[3] = "2,"
        } else if (foundFlags!!.contains("WH413L0RD")) {
            level[3] = "1,"
        }else {
            level[3] = "0,"
        }

        //InsecureLogging flags
        if(foundFlags!!.contains("D1V3T34M") && foundFlags!!.contains("F1SHF00D") && foundFlags!!.contains("T4STY-K3LP")){
            level[4] = "7"
        } else if(foundFlags!!.contains("F1SHF00D") && foundFlags!!.contains("T4STY-K3LP")){
            level[4] = "6"
        }else if(foundFlags!!.contains("D1V3T34M") && foundFlags!!.contains("T4STY-K3LP")){
            level[4] = "5"
        }else if(foundFlags!!.contains("D1V3T34M") && foundFlags!!.contains("F1SHF00D")){
            level[4] = "4"
        }else if(foundFlags!!.contains("D1V3T34M")){
            level[4] = "3"
        }else if(foundFlags!!.contains("F1SHF00D")){
            level[4] = "2"
        }else if(foundFlags!!.contains("T4STY-K3LP")){
            level[4] = "1"
        }else {
            level[4] = "0"
        }

        //TFA flags
        if(foundFlags!!.contains("P0K3RF4C3") && foundFlags!!.contains("H0N3YB33") && foundFlags!!.contains("C4SC4D1NG")){
            level[2] = "7,"
        } else if(foundFlags!!.contains("H0N3YB33") && foundFlags!!.contains("C4SC4D1NG")){
            level[2] = "6,"
        }else if(foundFlags!!.contains("P0K3RF4C3") && foundFlags!!.contains("C4SC4D1NG")){
            level[2] = "5,"
        }else if(foundFlags!!.contains("P0K3RF4C3") && foundFlags!!.contains("H0N3YB33")){
            level[2] = "4,"
        }else if(foundFlags!!.contains("P0K3RF4C3")){
            level[2] = "3,"
        }else if(foundFlags!!.contains("H0N3YB33")){
            level[2] = "2,"
        }else if(foundFlags!!.contains("C4SC4D1NG")){
            level[2] = "1,"
        }else {
            level[2] = "0,"
        }

        //SQLI flags
        if(foundFlags!!.contains("B055M4N") && foundFlags!!.contains("N1C3-W4N") && foundFlags!!.contains("4M4Z1NG")){
            level[1] = "7,"
        } else if(foundFlags!!.contains("N1C3-W4N") && foundFlags!!.contains("B055M4N")){
            level[1] = "6,"
        }else if(foundFlags!!.contains("4M4Z1NG") && foundFlags!!.contains("B055M4N")){
            level[1] = "5,"
        }else if(foundFlags!!.contains("4M4Z1NG") && foundFlags!!.contains("N1C3-W4N")){
            level[1] = "4,"
        }else if(foundFlags!!.contains("4M4Z1NG")){
            level[1] = "3,"
        }else if(foundFlags!!.contains("N1C3-W4N")){
            level[1] = "2,"
        }else if(foundFlags!!.contains("B055M4N")){
            level[1] = "1,"
        }else {
            level[1] = "0,"
        }

        //HardCoding flags
        if(foundFlags!!.contains("H3RM4N-M3LV1LL3") && foundFlags!!.contains("C0R4L-R33F") && foundFlags!!.contains("SP0NG3B0B")){
            level[0] = "7,"
        } else if(foundFlags!!.contains("C0R4L-R33F") && foundFlags!!.contains("SP0NG3B0B")){
            level[0] = "6,"
        }else if(foundFlags!!.contains("H3RM4N-M3LV1LL3") && foundFlags!!.contains("SP0NG3B0B")){
            level[0] = "5,"
        }else if(foundFlags!!.contains("H3RM4N-M3LV1LL3") && foundFlags!!.contains("C0R4L-R33F")){
            level[0] = "4,"
        }else if(foundFlags!!.contains("H3RM4N-M3LV1LL3")){
            level[0] = "3,"
        }else if(foundFlags!!.contains("C0R4L-R33F")){
            level[0] = "2,"
        }else if(foundFlags!!.contains("SP0NG3B0B")){
            level[0] = "1,"
        }else {
            level[0] = "0,"
        }

        println("1: $totalFlags")
        totalFlags = ""
        println("2: $totalFlags")
        for (i in 0..4) {
            totalFlags += level[i]
            println(i)
            println("-" + level)

        }
        println("3: $totalFlags")

    }

    private fun updateForm() { //Function to update all the progress bars and displays
        //Update all TextView displays
        txtTotal.text = "Total - $totalFound/15"
        txtEasy.text = "Easy - $easyFound/5"
        txtMed.text = "Medium - $medFound/5"
        txtKiller.text = "Killer - $killerFound/5"

        //Update all progress bars
        totalEasy.progress = (easyFound * 100) / 5
        totalMedium.progress = (medFound * 100) / 5
        totalKiller.progress = (killerFound * 100) / 5
        totalProgress.progress = (totalFound * 100) / 15
    }

    private fun errorMsg(case: Int) { //Function to display error message
        var title = ""
        when (case) { //Case statement based on parameter
            0 -> title = "Error: flag not found" //Incorrect flag was entered
            1 -> title = "This flag has already been entered" //Flag has already been entered
        }
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        builder.setCancelable(false)
            .setTitle(title)
            .setNegativeButton("Okay") { dialog, _ -> dialog.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun complete() { //Function for when user enters last flag and completes app
        val builder = AlertDialog.Builder(this, R.style.whaleDialog)
        builder.setCancelable(false)
            .setTitle("Congratulations!")
            .setMessage("You've successfully found all the flags! Whale done!") //Success message
            .setPositiveButton("Woohoo!") { dialog, _ -> dialog.cancel() }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}