package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper

class CriarJogoActivity : AppCompatActivity() {

    var idTorneio: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_jogo)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
        }

        val nomeEquipaCasa = findViewById<EditText>(R.id.txtNomeEquipaCasa_criarJogo)
        val nomeEquipaConvidada = findViewById<EditText>(R.id.txtNomeEquipaConvidada_criarJogo)
        val nomeArbitro = findViewById<EditText>(R.id.txtNomeArbitro_criarJogo)
        val dataJogo = findViewById<EditText>(R.id.txtDataJogo_criarJogo)

        val btGuardarDados = findViewById<Button>(R.id.btGuardarDados_criarJogo)

        btGuardarDados.setOnClickListener {
            VolleyHelper.instance.addGames(
                this@CriarJogoActivity,
                nomeEquipaCasa.text.toString(),
                nomeEquipaConvidada.text.toString(),
                nomeArbitro.text.toString(),
                dataJogo.text.toString(),
                idTorneio.toString()
            ) {
                if (it) {
                    //Mudar string
                    Toast.makeText(
                        this@CriarJogoActivity,
                        this@CriarJogoActivity.getString(R.string.createGame_success),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@CriarJogoActivity,
                        this@CriarJogoActivity.getString(R.string.createGame_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val intent = Intent(this@CriarJogoActivity, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}