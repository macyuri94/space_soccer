package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_ver_equipa_filtrados.*

class VerEquipaFiltradosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_ver_equipa_filtrados)

        painel1_verequipa_filtrado.setOnClickListener {
            val intent = Intent(this, VerJogadorActivity:: class.java)
            startActivity(intent)
        }

        bthome_verequipa_filtrado.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgperfiluser_verequipa_filtrado.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }
    }
}
