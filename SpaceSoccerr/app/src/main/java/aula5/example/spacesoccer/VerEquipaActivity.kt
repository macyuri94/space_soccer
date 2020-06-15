package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_ver_equipa.*

class VerEquipaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_ver_equipa)

        btcriarequipa_verequipa.setOnClickListener {
            val intent = Intent(this, CriarEquipaActivity:: class.java)
            startActivity(intent)
        }

        painel1_verequipa.setOnClickListener {
            val intent = Intent(this, InformacaoEquipaActivity:: class.java)
            startActivity(intent)
        }

        bthome_verequipa.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgperfiluser_verequipa.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

    }
}
