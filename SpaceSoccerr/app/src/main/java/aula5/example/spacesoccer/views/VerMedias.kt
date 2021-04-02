package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import aula5.example.spacesoccer.Equipas
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Estatisticas
import aula5.example.spacesoccer.models.Jogos
import aula5.example.spacesoccer.models.Torneios
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.informacao_torneio.*
import kotlinx.android.synthetic.main.ver_medias.*
import org.json.JSONObject

class VerMedias : AppCompatActivity() {

    var idTorneio       : Int? = null
    var nomeTorneio     : String? = null
    var email           : String? = null

    var listarTodasEquipas      : MutableList<Equipas> = ArrayList()
    var listarTodosJogos        : MutableList<Jogos> = ArrayList()
    var listarTodasEstatisticas : MutableList<Estatisticas> = ArrayList()
    var listarUtilizador        : MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_medias)

        val bundle = intent.extras
        bundle?.let {
            idTorneio   = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            email       = it.getString("Email")
        }

        txtTituloTorneio_verMedias.text = nomeTorneio

        val txtNumeroEquipas    = findViewById<TextView>(R.id.txtNumeroEquipas_verMedias)
        val txtNumeroJogos      = findViewById<TextView>(R.id.txtNumeroJogos_verMedias)
        val txtNumeroGolos      = findViewById<TextView>(R.id.txtNumeroGolos_verMedias)
        val txtMediaRemates     = findViewById<TextView>(R.id.txtMediaRemates_verMedias)
        val txtMediaFaltas      = findViewById<TextView>(R.id.txtMediaFaltas_verMedias)

        var numeroGolos:                Int = 0
        var numeroRematesTotais:        Int = 0
        var numeroFaltasTotais:         Int = 0

        var mediaRematesTotais:         Float = 0.2F
        var mediaFaltasTotais:          Float = 0.2F
        var mediaGolos:                 Float = 0.2F

        VolleyHelper.instance.getTournamentsClubById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarTodasEquipas.add(Equipas.parseJson(teamJSON))

                    txtNumeroEquipas.text = listarTodasEquipas.size.toString()
                }
            }
        }

        VolleyHelper.instance.getTournamentsGamesById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarTodosJogos.add(Jogos.parseJson(teamJSON))

                    txtNumeroJogos.text = listarTodosJogos.size.toString()
                }
            }
        }

        VolleyHelper.instance.getAllStatistic(this) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val gameJSON: JSONObject = it[index] as JSONObject
                    listarTodasEstatisticas.add(Estatisticas.parseJson(gameJSON))

                    numeroGolos         += listarTodasEstatisticas[index].GolosA!! + listarTodasEstatisticas[index].GolosB!!
                    numeroRematesTotais += listarTodasEstatisticas[index].RematesTotaisA!! + listarTodasEstatisticas[index].RematesTotaisB!!
                    numeroFaltasTotais  += listarTodasEstatisticas[index].NumIncidenciasA!! + listarTodasEstatisticas[index].NumIncidenciasB!!

                    mediaRematesTotais  = numeroRematesTotais.toFloat() / listarTodosJogos.size
                    mediaFaltasTotais   = numeroFaltasTotais.toFloat() / listarTodosJogos.size
                    mediaGolos          = numeroGolos.toFloat() / listarTodasEstatisticas.size

                    txtNumeroGolos.text     = mediaGolos.toString()
                    txtMediaRemates.text    = mediaRematesTotais.toString()
                    txtMediaFaltas.text     = mediaFaltasTotais.toString()
                }
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_Medias.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        btHome_verMedias.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("Email", email)
            startActivity(intent)
        }
    }
}