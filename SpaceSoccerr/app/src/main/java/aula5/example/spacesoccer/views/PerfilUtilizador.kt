package aula5.example.spacesoccer.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.perfil_utilizador.*
import kotlinx.android.synthetic.main.torneios.*
import org.json.JSONObject

class PerfilUtilizador : AppCompatActivity() {

    var email: String? = null

    var listarUtilizador    : MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_utilizador)

        val bundle = intent.extras
        bundle?.let {
            email = it.getString("Email")
        }
        
        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_PerfilUtilizador.text         = listarUtilizador[index].PrimeiroNome
                    txtultimoNome_PerfilUtilizador.text             = listarUtilizador[index].UltimoNome
                    txtPrimeiroNome_PerfilUtilizador.text           = listarUtilizador[index].PrimeiroNome
                    txtDataNascimento_PerfilUtilizador.text         = listarUtilizador[index].DataNascimento
                    txtEmail_PerfilUtilizador.text                  = listarUtilizador[index].Email
                }
            }
        }
    }
}