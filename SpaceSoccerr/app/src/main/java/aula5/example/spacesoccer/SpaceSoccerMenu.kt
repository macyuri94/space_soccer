package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_space_soccer_menu.*

class SpaceSoccerMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_menu)

        bthome.setOnClickListener {
            val intent = Intent(this, SpaceSoccerTorneios::class.java)
            startActivity(intent)
        }

        btequipasmenu.setOnClickListener {
            val intent = Intent(this, SpaceSoccerVerEquipa::class.java)
            startActivity(intent)
        }

        btjogadores.setOnClickListener {
            val intent = Intent(this, SpaceSoccerVerJogador::class.java)
            startActivity(intent)
        }

        imgperfiluser.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoJogador::class.java)
            startActivity(intent)
        }

    }
}
