package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import aula5.example.spacesoccer.Jogadores
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.Torneios
import aula5.example.spacesoccer.helper.VolleyHelper
import kotlinx.android.synthetic.main.torneios.*
import kotlinx.android.synthetic.main.torneios.btCriarTorneio_Torneio
import kotlinx.android.synthetic.main.torneios.imgPerfilUserTorneios_torneios
import kotlinx.android.synthetic.main.torneios_provisorio.*
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

class TorneiosActivity : AppCompatActivity() {

    /*var listarTorneios : MutableList<Torneios> = ArrayList()
    var torneiosAdapter : TorneiosActivity.TorneiosAdapter? = null */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.torneios_provisorio)


        imgPerfilUserTorneios_torneios.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        btCriarTorneio_Torneio.setOnClickListener {
            val intent = Intent(this, RegistarTorneioActivity::class.java)
            startActivity(intent)
        }

        imgPainelTorneio_torneios.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        txtNomeTorneio_torneios.text = "TorneioA"

        /*torneiosAdapter = TorneiosAdapter()
        listViewTorneios.adapter = torneiosAdapter

        VolleyHelper.instance.getAllTournaments(this ){ response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listarTorneios.add(Torneios.parseJson(jsonPlayer))
                }
                torneiosAdapter?.notifyDataSetChanged()
            }
        }*/

    }

    /* inner class TorneiosAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_torneios,parent,false)
            val nomeTorneio= rowView.findViewById<TextView>(R.id.txtNomeTorneio_torneios)

            nomeTorneio.text = listarTorneios[position].Nome

            return rowView
        }
        override fun getItem(position: Int): Any {
            return listarTorneios[position]
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getCount(): Int {
            return listarTorneios.size
        }

    }*/
}
