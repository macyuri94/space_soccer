package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.informacao_equipa.*
import kotlinx.android.synthetic.main.ver_equipa.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class InformacaoEquipaActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var IdClube: Int? = null
    var email: String? = null
    var NomeClube: String? = null
    var AnoFundacao: String? = null
    var Treinador: String? = null
    var CidadeFundacao: String? = null
    var nomeTorneio: String? = null

    var listarUtilizador    : MutableList<Utilizador> = ArrayList()

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
            email   = it.getString("Email")
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")

        }

        btHome_informacao_equipa.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        txtNomeEquipa_informacao_equipa.text = NomeClube
        txtAnoFundacao_informacao_equipa.text = AnoFundacao
        txtNomeEstadio_informacao_equipa.text = Treinador
        txtNomeLocalizacao_informacao_equipa.text = CidadeFundacao

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_informacaoEquipa.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

    }
}