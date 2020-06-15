package aula5.example.spacesoccer.views

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
import kotlinx.android.synthetic.main.ver_equipa.*
import org.json.JSONObject

class VerEquipaActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var idEquipa: Int? = null

    var listarEquipas : MutableList<Equipas> = ArrayList()
    var equipasAdapter : VerEquipaActivity.EquipasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_equipa)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")

        }

        btCriarEquipa_verEquipa.setOnClickListener {
            val intent = Intent(this, CriarEquipaActivity:: class.java)
            intent.putExtra("IdTorneio", idTorneio)
            startActivity(intent)
        }

        btHome_verEquipa.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_verEquipa.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        equipasAdapter = EquipasAdapter()
        listViewEquipas.adapter = equipasAdapter

        VolleyHelper.instance.getTournamentsClubById(this, idTorneio!!.toInt()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val teamJSON : JSONObject = it[index] as JSONObject
                    listarEquipas.add(Equipas.parseJson(teamJSON))
                }
                equipasAdapter?.notifyDataSetChanged()
            }
        }
    }

    inner class EquipasAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_equipas,parent,false)
            val nomeJogador = rowView.findViewById<TextView>(R.id.txtNomeJogador_verEquipa)

            val btApagar = rowView.findViewById<TextView>(R.id.btApagar_verEquipa)

            btApagar.setOnClickListener {
                val intent = Intent(this@VerEquipaActivity, MenuActivity::class.java)
                VolleyHelper.instance.deleteTeamsById(this@VerEquipaActivity, listarEquipas[position].IdClube!!) {response->
                    if(response){
                        //COLOCAR ALGO
                    } else{
                        Toast.makeText(
                            this@VerEquipaActivity,
                            this@VerEquipaActivity.getString(R.string.deleteTeam_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
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
