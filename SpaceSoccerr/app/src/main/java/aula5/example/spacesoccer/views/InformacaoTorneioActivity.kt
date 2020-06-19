package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.Equipas
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Torneios
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.informacao_equipa.*
import kotlinx.android.synthetic.main.informacao_jogador.*
import kotlinx.android.synthetic.main.informacao_torneio.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class InformacaoTorneioActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var nomeTorneio: String? = null
    var dtInicio: String? = null
    var dtFim: String? = null
    var NumEquipas: Int? = null
    var email: String? = null

    var listarTodasEquipas : MutableList<Equipas> = ArrayList()
    var listarUtilizador: MutableList<Utilizador> = ArrayList()
    var listarTorneio: MutableList<Torneios> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informacao_torneio)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            dtInicio = it.getString("dtInicio")
            dtFim = it.getString("dtFim")
            NumEquipas = it.getInt("NumEquipas")
            email = it.getString("Email")
        }

        VolleyHelper.instance.getTournamentsClubById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarTodasEquipas.add(Equipas.parseJson(teamJSON))

                    txtNumeroEquipas2_informacaoTorneio.text = listarTodasEquipas.size.toString()
                }
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_informacaoTorneio.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        btHome_informacaoTorneio.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        VolleyHelper.instance.getTournamentsId(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarTorneio.add(Torneios.parseJson(jsonPlayer))

                    txtTituloTorneio_informacaoTorneio.text = listarTorneio[index].Nome
                    txtNomeTorneio2_informacaoTorneio.text = listarTorneio[index].Nome
                    txtDataInicio2_informacaoTorneio.text = listarTorneio[index].dtInicio
                    txtDataFim2_informacaoTorneio.text = listarTorneio[index].dtFim
                }
            }
        }

        btMedias_informacaoTorneio.setOnClickListener{
            val intent = Intent(this, VerMedias::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("Email", email)
            startActivity(intent)
        }
    }
}