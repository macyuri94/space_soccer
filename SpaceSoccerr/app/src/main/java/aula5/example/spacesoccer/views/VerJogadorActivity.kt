package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.ver_jogador.*

class VerJogadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_jogador)

        btCriarJogador_verJogador.setOnClickListener {
            val intent = Intent(this, CriarEquipaActivity:: class.java)
            startActivity(intent)
        }

        btHome_verJogador.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_verJogador.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        painel1_verJogador.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }
    }
}
