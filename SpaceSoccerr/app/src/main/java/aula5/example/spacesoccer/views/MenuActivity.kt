package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.menu.*

class MenuActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var nomeTorneio: String? = null
    var dtInicio: String? = null
    var dtFim: String? = null
    var NumEquipas: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            dtInicio = it.getString("dtInicio")
            dtFim = it.getString("dtFim")
            NumEquipas = it.getInt("NumEquipas")
        }

        txtTituloTorneio_menu.text = nomeTorneio

        btHome_menu.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        btEquipasMenu_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            startActivity(intent)
        }

        btJogadores_menu.setOnClickListener {
            val intent = Intent(this, VerEquipaFiltradosActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            startActivity(intent)
        }

        imgPerfilUser_menu.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        /*btStats_menu.setOnClickListener {
            val intent = Intent(this, JogosActivity::class.java)
            startActivity(intent)
        }*/

        btJogos_menu.setOnClickListener {
            val intent = Intent(this, JogosActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            startActivity(intent)
        }

        btInfoTorneio_menu.setOnClickListener {
            val intent = Intent(this, InformacaoTorneioActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio!!.toInt())
            intent.putExtra("Nome", nomeTorneio)
            intent.putExtra("dtInicio", dtInicio)
            intent.putExtra("dtFim", dtFim)
            intent.putExtra("NumEquipas", NumEquipas)
            startActivity(intent)
        }
    }
}
