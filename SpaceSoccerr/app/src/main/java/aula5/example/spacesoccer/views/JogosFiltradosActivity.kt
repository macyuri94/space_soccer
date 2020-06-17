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
import kotlinx.android.synthetic.main.jogos.*
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class JogosFiltradosActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var nomeTorneio: String? = null

    var listarJogos: MutableList<Jogos> = ArrayList()
    var jogosAdapter: JogosFiltradosActivity.JogosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jogos_filtrados)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
        }

        jogosAdapter = JogosAdapter()
        listViewJogos.adapter = jogosAdapter

        VolleyHelper.instance.getTournamentsGamesById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarJogos.add(Jogos.parseJson(teamJSON))
                }
                jogosAdapter?.notifyDataSetChanged()
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