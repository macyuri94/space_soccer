package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Equipas
import aula5.example.spacesoccer.models.Torneios
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.informacao_equipa.*
import kotlinx.android.synthetic.main.row_equipa_filtrado.*
import kotlinx.android.synthetic.main.ver_equipa.*
import kotlinx.android.synthetic.main.ver_equipa_filtrados.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class VerEquipaFiltradosActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var email: String? = null
    var nomeTorneio: String? = null

    var listarEquipasFiltrado: MutableList<Equipas> = ArrayList()
    var equipasFiltradoAdapter: VerEquipaFiltradosActivity.EquipasFiltradoAdapter? = null

    var listarUtilizador    : MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_equipa_filtrados)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            email = it.getString("Email")
            nomeTorneio = it.getString("Nome")
        }

        equipasFiltradoAdapter = EquipasFiltradoAdapter()
        listViewEquipas_verEquipaFiltrado.adapter = equipasFiltradoAdapter

        VolleyHelper.instance.getTournamentsClubById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarEquipasFiltrado.add(Equipas.parseJson(teamJSON))
                }
                equipasFiltradoAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_verEquipaFiltrado.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        btHome_verEquipaFiltrado.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }
    }

    inner class EquipasFiltradoAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_equipa_filtrado, parent, false)
            val nomeEquipa = rowView.findViewById<TextView>(R.id.txtNomeEquipa_verEquipaFiltrado)

            rowView.setOnClickListener {
                val intent = Intent(this@VerEquipaFiltradosActivity, VerJogadorActivity::class.java)
                intent.putExtra("IdClube", listarEquipasFiltrado[position].IdClube)
                intent.putExtra("NomeClube", listarEquipasFiltrado[position].NomeClube)
                intent.putExtra("Email", email)
                intent.putExtra("IdTorneio", idTorneio!!.toInt())
                intent.putExtra("Nome", nomeTorneio)
                startActivity(intent)
            }

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