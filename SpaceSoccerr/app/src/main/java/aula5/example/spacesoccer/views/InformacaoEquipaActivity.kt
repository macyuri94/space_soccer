package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.informacao_equipa.*

// << ---------------------------------------------------------------------------------------------------------------- >> //

class InformacaoEquipaActivity : AppCompatActivity() {

    var nomeEquipa : String? = null
    var anoFundacao : String? = null
    var estadio : String ?= null
    var localizacao: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informacao_equipa)


        btHome_informacao_equipa.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_informacao_equipa.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }


        //Mudar isto por cenas a ir buscar dados a bd
        nomeEquipa = "Man United"
        estadio = "Old Traford"
        anoFundacao ="05/03/1878"
        localizacao = "Manchester, England"


        txtNomeEquipa_informacao_equipa.text = nomeEquipa
        txtAnoFundacao_informacao_equipa.text  = anoFundacao
        txtNomeEstadio_informacao_equipa.text = estadio
        txtNomeLocalizacao_informacao_equipa.text = localizacao
    }
}