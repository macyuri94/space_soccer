package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Equipas
import aula5.example.spacesoccer.models.Torneios
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.menu.*
import kotlinx.android.synthetic.main.torneios.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class MenuActivity : AppCompatActivity() {

    var idTorneio:      Int? = null
    var nomeTorneio:    String? = null
    var dtInicio:       String? = null
    var dtFim:          String? = null
    var NumEquipas:     Int? = null
    var email:          String? = null

    var listarUtilizador    : MutableList<Utilizador> = ArrayList()
    var listarTorneios    : MutableList<Torneios> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            dtInicio = it.getString("dtInicio")
            dtFim = it.getString("dtFim")
            NumEquipas = it.getInt("NumEquipas")
            email = it.getString("Email")
        }

        VolleyHelper.instance.getTournamentsId(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarTorneios.add(Torneios.parseJson(teamJSON))

                    txtTituloTorneio_menu.text = listarTorneios[index].Nome
                }
            }
        }

        btHome_menu.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        btEquipasMenu_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        btJogadores_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaFiltradosActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        imgPerfilUser_menu.setOnClickListener {
            val intent = Intent(this, PerfilUtilizador::class.java)
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        btStats_menu.setOnClickListener {
            val intent = Intent(this, JogosFiltradosActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        btJogos_menu.setOnClickListener {
            val intent = Intent(this, JogosActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        btInfoTorneio_menu.setOnClickListener {
            val intent = Intent(this, InformacaoTorneioActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("dtInicio", dtInicio)
            intent.putExtra("dtFim", dtFim)
            intent.putExtra("NumEquipas", NumEquipas)
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        btTorneio_menu.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            intent.putExtra("Email", email)
            startActivity(intent)
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_menu.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }
    }
}