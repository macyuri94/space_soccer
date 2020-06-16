package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.informacao_equipa.*

// << ---------------------------------------------------------------------------------------- >> //

class InformacaoEquipaActivity : AppCompatActivity() {

    var IdClube: Int? = null
    var NomeClube: String? = null
    var AnoFundacao: String? = null
    var Treinador: String? = null
    var CidadeFundacao: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informacao_equipa)

        val bundle = intent.extras
        bundle?.let {
            IdClube = it.getInt("IdClube")
            NomeClube = it.getString("NomeClube")
            AnoFundacao = it.getString("AnoFundacao")
            Treinador = it.getString("Treinador")
            CidadeFundacao = it.getString("CidadeFundacao")
        }

        btHome_informacao_equipa.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_informacao_equipa.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        txtNomeEquipa_informacao_equipa.text = NomeClube
        txtAnoFundacao_informacao_equipa.text = AnoFundacao
        txtNomeEstadio_informacao_equipa.text = Treinador
        txtNomeLocalizacao_informacao_equipa.text = CidadeFundacao

    }
}