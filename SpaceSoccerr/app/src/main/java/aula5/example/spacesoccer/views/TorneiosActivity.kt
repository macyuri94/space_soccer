package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Torneios
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.torneios.*
import kotlinx.android.synthetic.main.torneios.btCriarTorneio_Torneio
import kotlinx.android.synthetic.main.torneios.imgPerfilUserTorneios_torneios
import kotlinx.android.synthetic.main.torneios_provisorio.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class TorneiosActivity : AppCompatActivity() {

    var email: String? = null

    var listarUtilizador    : MutableList<Utilizador> = ArrayList()
    var listarTorneios      : MutableList<Torneios> = ArrayList()
    var torneiosAdapter     : TorneiosActivity.TorneiosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.torneios)

        torneiosAdapter = TorneiosAdapter()
        listViewTorneios.adapter = torneiosAdapter

        val bundle = intent.extras
        bundle?.let {
            email = it.getString("Email")
        }


        VolleyHelper.instance.getAllTournaments(this) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarTorneios.add(Torneios.parseJson(jsonPlayer))
                }
                torneiosAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_torneios.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        imgPerfilUserTorneios_torneios.setOnClickListener {
            val intent = Intent(this, PerfilUtilizador::class.java)
            intent.putExtra("Email", email.toString())
            startActivity(intent)
        }

        btCriarTorneio_Torneio.setOnClickListener {
            val intent = Intent(this, RegistarTorneioActivity::class.java)
            intent.putExtra("Email", email.toString())
            startActivity(intent)
        }
    }

    inner class TorneiosAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_torneios, parent, false)
            val nomeTorneio = rowView.findViewById<TextView>(R.id.txtNomeTorneio_torneios)

            rowView.setOnClickListener {
                val intent = Intent(this@TorneiosActivity, MenuActivity::class.java)
                intent.putExtra("IdTorneio", listarTorneios[position].IdTorneio)
                intent.putExtra("Nome", listarTorneios[position].Nome)
                intent.putExtra("dtInicio", listarTorneios[position].dtInicio)
                intent.putExtra("dtFim", listarTorneios[position].dtFim)
                intent.putExtra("NumEquipas", listarTorneios[position].NumEquipas)
                intent.putExtra("Email", email.toString())
                startActivity(intent)
            }

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
    }
}