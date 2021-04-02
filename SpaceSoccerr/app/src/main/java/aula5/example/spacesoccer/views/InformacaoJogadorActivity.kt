package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import aula5.example.spacesoccer.models.Utilizador
import kotlinx.android.synthetic.main.informacao_jogador.*
import kotlinx.android.synthetic.main.ver_jogador.*
import org.json.JSONObject

// << ---------------------------------------------------------------------------------------- >> //

class InformacaoJogadorActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var nomeEquipa: String? = null
    var IdJogador: Int? = null
    var Nome: String? = null
    var Nacionalidade: String? = null
    var DataNascimento: String? = null
    var email: String? = null
    var nomeTorneio: String? = null

    var listarUtilizador: MutableList<Utilizador> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informacao_jogador)

        val bundle = intent.extras
        bundle?.let {
            IdJogador = it.getInt("IdJogador")
            Nome = it.getString("Nome")
            Nacionalidade = it.getString("Nacionalidade")
            DataNascimento = it.getString("DataNascimento")
            nomeEquipa = it.getString("NomeClube")
            email = it.getString("Email")
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
        }

        VolleyHelper.instance.getUsersById(this, email.toString()) { response ->
            response?.let {
                for (index in 0 until it.length()) {
                    val jsonPlayer = it[index] as JSONObject
                    listarUtilizador.add(Utilizador.parseJson(jsonPlayer))

                    txtNomeUtilizador_informacaoJogador.text = listarUtilizador[index].PrimeiroNome
                }
            }
        }

        btHome_informacaoJogador.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("IdTorneio", idTorneio)
            intent.putExtra("Email", email)
            intent.putExtra("Nome", nomeTorneio)
            startActivity(intent)
        }

        txtNomeJogador_informacaoJogador.text = Nome
        txtDataNascimento_informacaoJogador.text = DataNascimento
        txtNacionalidade_informacaoJogador.text = Nacionalidade
        txtNomeClube_informacaoJogador.text = nomeEquipa
    }
}