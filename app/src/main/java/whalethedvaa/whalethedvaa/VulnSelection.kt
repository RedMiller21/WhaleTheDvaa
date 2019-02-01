package whalethedvaa.whalethedvaa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_vuln_selection2.*

class VulnSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vuln_selection2)

        //Call information dialog creation
        InformationBtn.setOnClickListener{
            informationDialog()
        }

        BackBtn.setOnClickListener{
            val intent = Intent(this, TheWhala::class.java)
            startActivity(intent)
        }

        ViewProgression.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }

        Vuln1.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",1)
            startActivity(intent)
        }
        Vuln2.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",2)
            startActivity(intent)
        }

        Vuln3.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",3)
            startActivity(intent)
        }

        Vuln4.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",4)
            startActivity(intent)
        }

        Vuln5.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",5)
            startActivity(intent)
        }

        Vuln6.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",6)
            startActivity(intent)
        }

        Vuln7.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",7)
            startActivity(intent)
        }

    }


    private fun informationDialog(){
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Vulnerability Information")
        builder.setMessage("Example Information")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}