package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.ver_equipa.*

class VerEquipaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_equipa)

        btCriarEquipa_verEquipa.setOnClickListener {
            val intent = Intent(this, CriarEquipaActivity:: class.java)
            startActivity(intent)
        }

        painel1_verEquipa.setOnClickListener {
            val intent = Intent(this, InformacaoEquipaActivity:: class.java)
            startActivity(intent)
        }

        btHome_verEquipa.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_verEquipa.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

    }
}
