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

class CriarJogadorActivity : AppCompatActivity() {

    var idEquipa: Int? = 0
    var email: String? = null
    var nomeTorneio: String? = null
    var idTorneio: Int? = null
    var nomeEquipa: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_jogador)

        val bundle = intent.extras
        bundle?.let {
            idEquipa = it.getInt("IdClube")
            idTorneio = it.getInt("IdTorneio")
            email = it.getString("Email")
            nomeTorneio = it.getString("Nome")
            nomeEquipa = it.getString("NomeClube")
        }

        val numberCc = findViewById<EditText>(R.id.txtNumeroCartaoCidadao_criarJogador)
        val name = findViewById<EditText>(R.id.txtNomeJogador_criarJogadorr)
        val number = findViewById<EditText>(R.id.txtNumero_criarJogador)
        val birthDate = findViewById<EditText>(R.id.txtDataNascimento_criarJogador)
        val nationality = findViewById<EditText>(R.id.txtNacionalidade_criarJogador)
        val position = findViewById<EditText>(R.id.txtPosicao_criarJogador)
        val height = findViewById<EditText>(R.id.txtAltura_criarJogador)
        val weight = findViewById<EditText>(R.id.txtPeso_criarJogador)

        val btGuardarDados_criarJogador = findViewById<Button>(R.id.btGuardarDados_criarJogador)

        btGuardarDados_criarJogador.setOnClickListener {
            VolleyHelper.instance.addPlayers(
                this@CriarJogadorActivity,
                numberCc.text.toString(),
                name.text.toString(),
                number.text.toString(),
                birthDate.text.toString(),
                nationality.text.toString(),
                position.text.toString(),
                height.text.toString(),
                weight.text.toString(),
                idEquipa.toString()
            ) {
                if (it) {
                    //Mudar string
                    Toast.makeText(
                        this@CriarJogadorActivity,
                        this@CriarJogadorActivity.getString(R.string.creatPlayer_success),
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(this@CriarJogadorActivity, VerJogadorActivity::class.java)
                    intent.putExtra("IdClube", idEquipa)
                    intent.putExtra("Email", email)
                    intent.putExtra("IdTorneio", idTorneio)
                    intent.putExtra("Nome", nomeTorneio)
                    intent.putExtra("NomeClube", nomeEquipa)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        this@CriarJogadorActivity,
                        this@CriarJogadorActivity.getString(R.string.creatPlayer_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}