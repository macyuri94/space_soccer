package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Estatisticas
import aula5.example.spacesoccer.models.Jogadores
import aula5.example.spacesoccer.models.Jogos
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.estatisticas.*
import kotlinx.android.synthetic.main.jogos.*
import kotlinx.android.synthetic.main.jogos.listViewJogos
import kotlinx.android.synthetic.main.jogos_filtrados.*
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class JogosFiltradosActivity : AppCompatActivity() {

    var idTorneio:      Int? = null
    var nomeTorneio:    String? = null

    var email:          String? = null

    var listarJogos:        MutableList<Jogos> = ArrayList()
    var jogosAdapter:       JogosFiltradosActivity.JogosAdapter? = null
    var listarUtilizador:   MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jogos_filtrados)

        val bundle = intent.extras
        bundle?.let {
            idTorneio   = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            email       = it.getString("Email")
        }

        jogosAdapter = JogosAdapter()
        listViewJogos.adapter = jogosAdapter

        btHome_JogosFiltrados.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio)
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        VolleyHelper.instance.getTournamentsGamesById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarJogos.add(Jogos.parseJson(teamJSON))
                }
                jogosAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_Jogos.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }
    }

    inner class JogosAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_jogos, parent, false)
            val nomeEquipaA = rowView.findViewById<TextView>(R.id.text_NomeEquipaA_Jogos)
            val nomeEquipaB = rowView.findViewById<TextView>(R.id.text_NomeEquipaB_Jogos)
            val dataJogo = rowView.findViewById<TextView>(R.id.text_DataJogo_Jogos)

            rowView.setOnClickListener {
                val intent = Intent(this@JogosFiltradosActivity, EstatisticasActivity::class.java)
                intent.putExtra("IdJogo", listarJogos[position].IdJogo)
                intent.putExtra("EquipaCasa", listarJogos[position].EquipaCasa)
                intent.putExtra("EquipaConvidada", listarJogos[position].EquipaConvidada)
                intent.putExtra("DataJogo", listarJogos[position].DataJogo)
                intent.putExtra("Nome", nomeTorneio)
                intent.putExtra("Email", email)
                intent.putExtra("IdTorneio", idTorneio)
                intent.putExtra("Nome", nomeTorneio)
                startActivity(intent)
            }

            nomeEquipaA.text = listarJogos[position].EquipaCasa
            nomeEquipaB.text = listarJogos[position].EquipaConvidada
            dataJogo.text = listarJogos[position].DataJogo

            return rowView
        }

        override fun getItem(position: Int): Any {
            return listarJogos[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listarJogos.size
        }

    }
}