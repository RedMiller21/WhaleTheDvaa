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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_page)

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
                    // get user input and set it to result
                    // edit text
                    //Toast.makeText(this, "Entered: " + userInput.text.toString(), Toast.LENGTH_LONG).show()
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
    }
}


