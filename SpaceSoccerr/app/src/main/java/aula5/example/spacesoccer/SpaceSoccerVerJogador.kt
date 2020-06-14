package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_menu.*
import kotlinx.android.synthetic.main.activity_space_soccer_ver_equipa.*
import kotlinx.android.synthetic.main.activity_space_soccer_ver_jogador.*

class SpaceSoccerVerJogador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_ver_jogador)


        bthomeverjogadores.setOnClickListener {
            val intent = Intent(this, SpaceSoccerTorneios::class.java)
            startActivity(intent)
        }

        imgperfiluserverjogador.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoJogador::class.java)
            startActivity(intent)
        }

        painel1verjogador.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoJogador::class.java)
            startActivity(intent)
        }
    }
}
