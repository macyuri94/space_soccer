package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Equipas
import kotlinx.android.synthetic.main.ver_equipa.*
import kotlinx.android.synthetic.main.ver_equipa_filtrados.*
import org.json.JSONObject

class VerEquipaFiltradosActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var listarEquipasFiltrado : MutableList<Equipas> = ArrayList()
    var equipasFiltradoAdapter : VerEquipaFiltradosActivity.EquipasFiltradoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_equipa_filtrados)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
        }

        equipasFiltradoAdapter = EquipasFiltradoAdapter()
        listViewEquipas_verEquipaFiltrado.adapter = equipasFiltradoAdapter

        VolleyHelper.instance.getAllTeams(this ){ response ->
            response?.let {
                for(index in 0 until it.length()){
                    val jsonPlayer = it[index] as JSONObject
                    listarEquipasFiltrado.add(Equipas.parseJson(jsonPlayer))
                }
                equipasFiltradoAdapter?.notifyDataSetChanged()
            }
        }

        btHome_verEquipaFiltrado.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_verEquipaFiltrado.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }
    }

    inner class EquipasFiltradoAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_equipa_filtrado,parent,false)
            val nomeEquipa = rowView.findViewById<TextView>(R.id.txtNomeEquipa_verEquipaFiltrado)

            nomeEquipa.text = listarEquipasFiltrado[position].NomeClube

            return rowView
        }
        override fun getItem(position: Int): Any {
            return listarEquipasFiltrado[position]
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getCount(): Int {
            return listarEquipasFiltrado.size
        }

    }
}
