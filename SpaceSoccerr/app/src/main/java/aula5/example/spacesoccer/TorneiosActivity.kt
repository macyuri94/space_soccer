package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_torneios.*

class TorneiosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_torneios)

        img_painel_torneio_torneios.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        imgperfilusertorneios_torneios.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        txtnometorneio_torneios.text = "Torneio A"
    }
}
