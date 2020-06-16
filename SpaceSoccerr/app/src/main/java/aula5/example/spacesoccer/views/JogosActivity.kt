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
import aula5.example.spacesoccer.models.Jogadores
import aula5.example.spacesoccer.models.Jogos
import kotlinx.android.synthetic.main.jogos.*
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class JogosActivity : AppCompatActivity() {

    var idTorneio: Int? = null

    var listarJogos: MutableList<Jogos> = ArrayList()
    var jogosAdapter: JogosActivity.JogosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jogos)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
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

        btCriarJogos_Jogos.setOnClickListener {
            val intent = Intent(this, CriarJogoActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            startActivity(intent)
        }
    }

    inner class JogosAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_jogos, parent, false)
            val nomeEquipaA = rowView.findViewById<TextView>(R.id.text_NomeEquipaA_Jogos)
            val nomeEquipaB = rowView.findViewById<TextView>(R.id.text_NomeEquipaB_Jogos)
            val dataJogo = rowView.findViewById<TextView>(R.id.text_DataJogo_Jogos)

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