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
            onBackPressed()
        }

        ViewProgression.setOnClickListener{
            val intent = Intent(this, ProgressPage::class.java)
            startActivity(intent)
        }

        Vuln1.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",1)
            intent.putExtra("name",Vuln1.text)
            startActivity(intent)
        }
        Vuln2.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",2)
            intent.putExtra("name",Vuln2.text)
            startActivity(intent)
        }

        Vuln3.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",3)
            intent.putExtra("name",Vuln3.text)
            startActivity(intent)
        }

        Vuln4.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",4)
            intent.putExtra("name",Vuln4.text)
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

        Vuln7.setOnClickListener{
            val intent = Intent(this, DifficultySelector::class.java)
            intent.putExtra("vulnerability",7)
            intent.putExtra("name",Vuln7.text)
            startActivity(intent)
        }

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