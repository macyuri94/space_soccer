package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import aula5.example.spacesoccer.models.Jogadores
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Equipas
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.ver_equipa.*
import kotlinx.android.synthetic.main.ver_equipa_filtrados.*
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class VerJogadorActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var idEquipa: Int? = null
    var nomeEquipa: String? = null
    var email : String? = null
    var nomeTorneio: String? = null

    var listarJogadores: MutableList<Jogadores> = ArrayList()
    var jogadoresAdapter: JogadoresAdapter? = null
    var listarUtilizador: MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_jogador)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            idEquipa = it.getInt("IdClube")
            nomeEquipa = it.getString("NomeClube")
            email = it.getString("Email")
            nomeTorneio = it.getString("Nome")

        }

        btCriarJogador_verJogador.setOnClickListener {
            val intent = Intent(this, CriarJogadorActivity::class.java)
            intent.putExtra("IdClube", idEquipa!!.toInt())
            intent.putExtra("Email", email)
            intent.putExtra("IdTorneio", idTorneio)
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("NomeClube", nomeEquipa)
            startActivity(intent)
        }


        jogadoresAdapter = JogadoresAdapter()
        listViewJogadores.adapter = jogadoresAdapter

        VolleyHelper.instance.getPlayersById(this, idEquipa!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarJogadores.add(Jogadores.parseJson(teamJSON))
                }
                jogadoresAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_verJogador.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        btHome_verJogador.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio)
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }
    }

    inner class JogadoresAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_jogadores, parent, false)
            val nomeJogador = rowView.findViewById<TextView>(R.id.txtNomeJogador_verJogador)

            rowView.setOnClickListener {
                val intent = Intent(this@VerJogadorActivity, InformacaoJogadorActivity::class.java)
                intent.putExtra("NomeClube", nomeEquipa)
                intent.putExtra("IdJogador", listarJogadores[position].IdJogador)
                intent.putExtra("Nome", listarJogadores[position].Nome)
                intent.putExtra("Nacionalidade", listarJogadores[position].Nacionalidade)
                intent.putExtra("DataNascimento", listarJogadores[position].DataNascimento)
                intent.putExtra("Email", email)
                intent.putExtra("IdTorneio", idTorneio!!.toInt())
                intent.putExtra("Nome", nomeTorneio)
                intent.putExtra("IdClube", idEquipa!!.toInt())
                startActivity(intent)
            }

            nomeJogador.text = listarJogadores[position].Nome

            return rowView
        }

        override fun getItem(position: Int): Any {
            return listarJogadores[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listarJogadores.size
        }
    }
}