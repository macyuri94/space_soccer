package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_informacao_equipa.*
import kotlinx.android.synthetic.main.activity_space_soccer_informacao_jogador.*

class SpaceSoccerInformacaoJogador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_informacao_jogador)

        bthomeinformacaojogador.setOnClickListener {
            val intent = Intent(this, SpaceSoccerTorneios::class.java)
            startActivity(intent)
        }

        imgperfiluserinformacaojogador.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoJogador::class.java)
            startActivity(intent)
        }
    }
}
