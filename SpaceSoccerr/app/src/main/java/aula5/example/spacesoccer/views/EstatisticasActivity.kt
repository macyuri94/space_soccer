package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Estatisticas
import aula5.example.spacesoccer.models.Jogos
import aula5.example.spacesoccer.models.Torneios
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.estatisticas.*
import kotlinx.android.synthetic.main.jogos.*
import kotlinx.android.synthetic.main.menu.*
import kotlinx.android.synthetic.main.row_estatistica_painel1.*
import kotlinx.android.synthetic.main.row_estatisticas.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class EstatisticasActivity : AppCompatActivity() {

    var IdTorneio: Int? = null
    var IdJogo: Int? = null
    var EquipaCasa: String? = null
    var EquipaConvidada: String? = null
    var DataJogo: String? = null
    var nomeTorneio: String? = null

    var email:          String? = null

    var listarEstatistica           : MutableList<Estatisticas> = ArrayList()
    var estatisticaAdapter          : EstatisticasActivity.EstatisticaAdapter? = null

    var listarEstatisticaPainel1    : MutableList<Estatisticas> = ArrayList()
    var estatisticaAdapterPainel1   : EstatisticasActivity.EstatisticaPainel1Adapter? = null

    var listarUtilizador            : MutableList<Utilizador> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estatisticas)

        val bundle = intent.extras
        bundle?.let {
            IdTorneio       = it.getInt("IdTorneio")
            IdJogo          = it.getInt("IdJogo")
            EquipaCasa      = it.getString("EquipaCasa")
            EquipaConvidada = it.getString("EquipaConvidada")
            DataJogo        = it.getString("DataJogo")
            nomeTorneio     = it.getString("Nome")
            email           = it.getString("Email")
        }


        estatisticaAdapter = EstatisticaAdapter()
        listViewEstatistica.adapter = estatisticaAdapter

        estatisticaAdapterPainel1 = EstatisticaPainel1Adapter()
        listViewEstatistica_painel1.adapter = estatisticaAdapterPainel1

        VolleyHelper.instance.getEstatisticaGamesById(this, IdJogo!!) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarEstatistica.add(Estatisticas.parseJson(teamJSON))
                }
                estatisticaAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getEstatisticaGamesById(this, IdJogo!!) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarEstatisticaPainel1.add(Estatisticas.parseJson(teamJSON))

                }
                estatisticaAdapterPainel1?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_estatisticas.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        bthome_estatisticas.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", IdTorneio)
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }
    }

    inner class EstatisticaAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_estatisticas, parent, false)
            val possedeBolaA = rowView.findViewById<TextView>(R.id.txtTotalPosseDeBolaEquipaA_estatisticas)
            val possedeBolaB = rowView.findViewById<TextView>(R.id.txtTotalPosseDeBolaEquipaB_estatisticas)
            val rematesA = rowView.findViewById<TextView>(R.id.txtTotalRematesEquipaA_estatisticas)
            val rematesB = rowView.findViewById<TextView>(R.id.txtTotalRematesEquipaB_estatisticas)
            val rematesBalizaA = rowView.findViewById<TextView>(R.id.txtRematesBalizaEquipaA_estatisticas)
            val rematesBalizaB = rowView.findViewById<TextView>(R.id.txtRematesBalizaEquipaB_estatisticas)
            val incidenciasA = rowView.findViewById<TextView>(R.id.txtIncidenciasEquipaA_estatisticas)
            val incidenciasB = rowView.findViewById<TextView>(R.id.txtIncidenciasEquipaB_estatisticas)
            val foradeJogoA = rowView.findViewById<TextView>(R.id. txtForaDeJogoEquipaA_estatisticas)
            val foradeJogoB = rowView.findViewById<TextView>(R.id. txtForaDeJogoEquipaB_estatisticas)


            possedeBolaA.text = listarEstatistica[position].PosseBolaA.toString()
            possedeBolaB.text = listarEstatistica[position].PosseBolaB.toString()
            rematesA.text = listarEstatistica[position].RematesTotaisA.toString()
            rematesB.text = listarEstatistica[position].RematesTotaisB.toString()
            rematesBalizaA.text = listarEstatistica[position].RematesBalizaA.toString()
            rematesBalizaB.text = listarEstatistica[position].RematesBalizaB.toString()
            incidenciasA.text = listarEstatistica[position].NumIncidenciasA.toString()
            incidenciasB.text = listarEstatistica[position].NumIncidenciasB.toString()
            foradeJogoA.text = listarEstatistica[position].ForaDeJogoA.toString()
            foradeJogoB.text = listarEstatistica[position].ForaDeJogoB.toString()


            return rowView
        }

        override fun getItem(position: Int): Any {
            return listarEstatistica[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listarEstatistica.size
        }

    }

    inner class EstatisticaPainel1Adapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView2 = layoutInflater.inflate(R.layout.row_estatistica_painel1, parent, false)
            val NomeTorneio = rowView2.findViewById<TextView>(R.id.txtnometorneio_estatisticas)
            val nomeEquipaA = rowView2.findViewById<TextView>(R.id.txtnomequipa1_estatisticas)
            val nomeEquipaB = rowView2.findViewById<TextView>(R.id.txtnomequipa2_estatisticas)
            val tempoFinal  = rowView2.findViewById<TextView>(R.id.txttempofinal_estatisticas)
            val dataJogo  = rowView2.findViewById<TextView>(R.id.txtdatajogo_estatisticas)
            val goloA = rowView2.findViewById<TextView>(R.id.txtresultadoequipa1_estatisticas)
            val goloB = rowView2.findViewById<TextView>(R.id.txtresultadoequipa2_estatisticas)

            goloA.text = listarEstatisticaPainel1[position].GolosA.toString()
            goloB.text = listarEstatisticaPainel1[position].GolosB.toString()

            NomeTorneio.text = nomeTorneio
            nomeEquipaA.text = EquipaCasa
            nomeEquipaB.text = EquipaConvidada
            tempoFinal.text = "TF"
            dataJogo.text = DataJogo


            return rowView2
        }

        override fun getItem(position: Int): Any {
            return listarEstatisticaPainel1[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listarEstatisticaPainel1.size
        }

    }

}