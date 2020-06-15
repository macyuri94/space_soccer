package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        btHome_menu.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        btEquipasMenu_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaActivity::class.java)
            startActivity(intent)
        }

        btJogadores_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaFiltradosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_menu.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        btStats_menu.setOnClickListener {
            val intent = Intent(this, JogosActivity::class.java)
            startActivity(intent)
        }
    }
}
