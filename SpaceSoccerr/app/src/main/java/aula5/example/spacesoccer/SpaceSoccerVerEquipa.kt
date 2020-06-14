package aula5.example.spacesoccer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_space_soccer_menu.*
import kotlinx.android.synthetic.main.activity_space_soccer_torneios.*
import kotlinx.android.synthetic.main.activity_space_soccer_ver_equipa.*

class SpaceSoccerVerEquipa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_ver_equipa)

        btcriarequipa.setOnClickListener {
            val intent = Intent(this, SpaceSoccerCriarEquipa:: class.java)
            startActivity(intent)
        }

        painel1verequipa.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoEquipa:: class.java)
            startActivity(intent)
        }

        bthomeverequipa.setOnClickListener {
            val intent = Intent(this, SpaceSoccerTorneios::class.java)
            startActivity(intent)
        }

        imgperfiluserverequipa.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoJogador::class.java)
            startActivity(intent)
        }

    }
}
