package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_menu.*
import kotlinx.android.synthetic.main.activity_space_soccer_torneios.*

class SpaceSoccerTorneios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_torneios)

        img_painel_torneio.setOnClickListener {
            val intent = Intent(this, SpaceSoccerMenu::class.java)
            startActivity(intent)
        }

        btnometorneio.setOnClickListener {
            val intent = Intent(this, SpaceSoccerMenu::class.java)
            startActivity(intent)
        }
    }
}
