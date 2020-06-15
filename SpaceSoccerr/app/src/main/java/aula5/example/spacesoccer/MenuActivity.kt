package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_menu)

        bthome_menu.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        btequipasmenu_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaActivity::class.java)
            startActivity(intent)
        }

        btjogadores_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaFiltradosActivity::class.java)
            startActivity(intent)
        }

        imgperfiluser_menu.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        btstats_menu.setOnClickListener {
            val intent = Intent(this, JogosActivity::class.java)
            startActivity(intent)
        }
    }
}
