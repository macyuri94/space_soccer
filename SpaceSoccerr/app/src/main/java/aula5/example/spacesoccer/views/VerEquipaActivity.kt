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
import aula5.example.spacesoccer.models.Equipas
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.menu.*
import kotlinx.android.synthetic.main.row_equipas.*
import kotlinx.android.synthetic.main.ver_equipa.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class VerEquipaActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var email: String? = null
    var nomeTorneio: String? = null
    var listarEquipas: MutableList<Equipas> = ArrayList()
    var equipasAdapter: VerEquipaActivity.EquipasAdapter? = null

    var listarUtilizador    : MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_equipa)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            email = it.getString("Email")
            nomeTorneio = it.getString("Nome")
        }

        btCriarEquipa_verEquipa.setOnClickListener {
            val intent = Intent(this, CriarEquipaActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio)
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        btHome_verEquipa.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio)
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        equipasAdapter = EquipasAdapter()
        listViewEquipas.adapter = equipasAdapter

        VolleyHelper.instance.getTournamentsClubById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON: JSONObject = it[index] as JSONObject
                    listarEquipas.add(Equipas.parseJson(teamJSON))
                }
                equipasAdapter?.notifyDataSetChanged()
            }
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_verEquipa.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }
    }

    inner class EquipasAdapter : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_equipas, parent, false)
            val nomeJogador = rowView.findViewById<TextView>(R.id.txtNomeJogador_verEquipa)

            val btApagar = rowView.findViewById<TextView>(R.id.btApagar_verEquipa)

            btApagar.setOnClickListener {
                VolleyHelper.instance.deleteTeamsById(
                    this@VerEquipaActivity,
                    listarEquipas[position].IdClube!!
                ) { response ->
                    if (response) {
                        //COLOCAR ALGO
                        Toast.makeText(
                            this@VerEquipaActivity,
                            this@VerEquipaActivity.getString(R.string.deleteTeam_success),
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this@VerEquipaActivity, MenuActivity::class.java)
                        intent.putExtra("IdTorneio", idTorneio)
                        intent.putExtra("Email", email)
                        intent.putExtra("Nome", nomeTorneio)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@VerEquipaActivity,
                            this@VerEquipaActivity.getString(R.string.deleteTeam_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                startActivity(intent)
            }

            rowView.setOnClickListener {
                val intent = Intent(this@VerEquipaActivity, InformacaoEquipaActivity::class.java)
                intent.putExtra("IdTorneio", idTorneio!!.toInt())
                intent.putExtra("IdClube", listarEquipas[position].IdClube)
                intent.putExtra("NomeClube", listarEquipas[position].NomeClube)
                intent.putExtra("AnoFundacao", listarEquipas[position].AnoFundacao)
                intent.putExtra("Treinador", listarEquipas[position].Treinador)
                intent.putExtra("CidadeFundacao", listarEquipas[position].CidadeFundacao)
                intent.putExtra("Email", email)
                intent.putExtra("Nome", nomeTorneio)
                startActivity(intent)
            }

            nomeJogador.text = listarEquipas[position].NomeClube

            return rowView
        }

        override fun getItem(position: Int): Any {
            return listarEquipas[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listarEquipas.size
        }
    }
}