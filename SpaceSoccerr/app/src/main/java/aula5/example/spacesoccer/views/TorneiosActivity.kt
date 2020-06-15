package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.torneios.*

class TorneiosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.torneios)

        imgPainelTorneio_torneios.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUserTorneios_torneios.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        txtNomeTorneio_torneios.text = "Torneio A"
    }
}
