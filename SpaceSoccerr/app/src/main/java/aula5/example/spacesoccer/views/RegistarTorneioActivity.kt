package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import kotlinx.android.synthetic.main.registar_torneio.*

// << ---------------------------------------------------------------------------------------- >> //

class RegistarTorneioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registar_torneio)

        val name = findViewById<EditText>(R.id.textTournamentName_registar_torneio)
        val dtstart = findViewById<EditText>(R.id.textStartDate_registar_torneio)
        val dtend = findViewById<EditText>(R.id.textEndDate_registar_torneio)
        val numberteams = findViewById<EditText>(R.id.textNumberOfTeams_registar_torneio)

        buttonSaveData_registar_torneio.setOnClickListener {
            VolleyHelper.instance.addTournament(
                this@RegistarTorneioActivity,
                name.text.toString(),
                dtstart.text.toString(),
                dtend.text.toString(),
                numberteams.text.toString()
            ) {
                if (it) {
                    //Mudar string
                    Toast.makeText(
                        this@RegistarTorneioActivity,
                        this@RegistarTorneioActivity.getString(R.string.creatTournament_failed),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@RegistarTorneioActivity,
                        this@RegistarTorneioActivity.getString(R.string.creatTournament_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val intent = Intent(this@RegistarTorneioActivity, TorneiosActivity::class.java)
            startActivity(intent)
        }
    }
}