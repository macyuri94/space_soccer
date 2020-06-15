package aula5.example.spacesoccer.views

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
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

class VerJogadorActivity : AppCompatActivity() {

    var listarJogadores : MutableList<Jogadores> = ArrayList()
    var jogadoresAdapter : JogadoresAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_jogador)

        btCriarJogador_verJogador.setOnClickListener {
            val intent = Intent(this, CriarJogadorActivity:: class.java)
            startActivity(intent)
        }

        jogadoresAdapter = JogadoresAdapter()
        listViewJogadores.adapter = jogadoresAdapter

        VolleyHelper.instance.getAllPlayers(this ){response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listarJogadores.add(Jogadores.parseJson(jsonPlayer))
                }
                jogadoresAdapter?.notifyDataSetChanged()
            }
        }
    }

    inner class JogadoresAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_jogadores,parent,false)
            val nomeJogador = rowView.findViewById<TextView>(R.id.txtNomeJogador_verJogador)

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
