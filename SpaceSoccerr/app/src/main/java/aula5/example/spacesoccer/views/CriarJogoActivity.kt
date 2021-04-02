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
    var nomeTorneio : String? = null
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_jogo)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            email = it.getString("Email")
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
                    val intent = Intent(this@CriarJogoActivity, JogosActivity::class.java)
                    intent.putExtra("IdTorneio", idTorneio)
                    intent.putExtra("Email", email)
                    intent.putExtra("Nome", nomeTorneio)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@CriarJogoActivity,
                        this@CriarJogoActivity.getString(R.string.createGame_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}