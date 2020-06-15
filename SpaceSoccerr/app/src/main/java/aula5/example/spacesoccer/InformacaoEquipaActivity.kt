package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_space_soccer_informacao_equipa.*

class InformacaoEquipaActivity : AppCompatActivity() {

    var NomeEquipa : String? = null
    var AnoFundacao : String? = null
    var Estadio : String ?= null
    var localizacao: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_informacao_equipa)


        bthome_informacao_equipa.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgperfiluser_informacao_equipa.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }


        //Mudar isto por cenas a ir buscar dados a bd
        NomeEquipa = "Man United"
        Estadio = "Old Traford"
        AnoFundacao ="05/03/1878"
        localizacao = "Manchester, England"


        txtnomequipa_informacao_equipa.text = NomeEquipa
        txtanofundacao_informacao_equipa.text  =AnoFundacao
        txtnomestadio_informacao_equipa.text = Estadio
        txtnomelocalizacao_informacao_equipa.text = localizacao
    }
}
