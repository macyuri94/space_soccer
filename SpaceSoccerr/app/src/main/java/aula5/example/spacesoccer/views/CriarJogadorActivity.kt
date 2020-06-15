package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper

// << ---------------------------------------------------------------------------------------------------------------- >> //

class CriarJogadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_jogador)

        val numerocartaocidadao = findViewById<EditText>(R.id.txtNumeroCartaoCidadao_criarJogador)
        val name = findViewById<EditText>(R.id.txtNomeJogador_criarJogadorr)
        val number = findViewById<EditText>(R.id.txtNumero_criarJogador)
        val birthDate = findViewById<EditText>(R.id.txtDataNascimento_criarJogador)
        val nationality = findViewById<EditText>(R.id.txtNacionalidade_criarJogador)
        val position = findViewById<EditText>(R.id.txtPosicao_criarJogador)
        val height = findViewById<EditText>(R.id.txtAltura_criarJogador)
        val weight = findViewById<EditText>(R.id.txtPeso_criarJogador)
        val team = findViewById<EditText>(R.id.txtClube_criarJogador)

        val btGuardarDados_criarJogador=findViewById<Button>(R.id.btGuardarDados_criarJogador)

        btGuardarDados_criarJogador.setOnClickListener {
            VolleyHelper.instance.addPlayers(
                this@CriarJogadorActivity,
                numerocartaocidadao.text.toString(),
                name.text.toString(),
                number.text.toString(),
                birthDate.text.toString(),
                nationality.text.toString(),
                position.text.toString(),
                height.text.toString(),
                weight.text.toString(),
                team.text.toString()
            ) {
                if (it) {
                    val intent = Intent(this@CriarJogadorActivity, VerEquipaActivity::class.java)
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
