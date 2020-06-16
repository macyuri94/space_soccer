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

// << ---------------------------------------------------------------------------------------- >> //

class CriarEquipaActivity : AppCompatActivity() {

    var idtorneio: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_equipa)

        val bundle = intent.extras
        bundle?.let {
            idtorneio = it.getInt("IdTorneio")
        }

        val nome = findViewById<EditText>(R.id.txtNomeEquipa_criarEquipa)
        val president = findViewById<EditText>(R.id.txtPresidente_criarEquipa)
        val coach = findViewById<EditText>(R.id.txtTreinador_criarEquipa)
        val yearfundation = findViewById<EditText>(R.id.txtAnoFundacao_criarEquipa)
        val cityfundation = findViewById<EditText>(R.id.txtCidadeFundacao_criarEquipa)

        val btGuardDado = findViewById<Button>(R.id.btGuardarDados_criarEquipa)

        btGuardDado.setOnClickListener {
            VolleyHelper.instance.addTeams(
                this@CriarEquipaActivity,
                idtorneio.toString(),
                nome.text.toString(),
                president.text.toString(),
                coach.text.toString(),
                yearfundation.text.toString(),
                cityfundation.text.toString()
            ) {
                if (it) {
                    //Mudar string
                    Toast.makeText(
                        this@CriarEquipaActivity,
                        this@CriarEquipaActivity.getString(R.string.creatTeam_failed),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@CriarEquipaActivity,
                        this@CriarEquipaActivity.getString(R.string.creatTeam_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val intent = Intent(this@CriarEquipaActivity, VerEquipaActivity::class.java)
            startActivity(intent)
        }
    }
}