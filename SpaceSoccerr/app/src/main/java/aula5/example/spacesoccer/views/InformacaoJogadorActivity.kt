package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.informacao_jogador.*

// << ---------------------------------------------------------------------------------------- >> //

class InformacaoJogadorActivity : AppCompatActivity() {

    var nomeEquipa: String? = null
    var IdJogador: Int? = null
    var Nome: String? = null
    var Nacionalidade: String? = null
    var DataNascimento: String? = null

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
        }

        btHome_informacaoJogador.setOnClickListener {
            val intent = Intent(this, TorneiosActivity::class.java)
            startActivity(intent)
        }

        imgPerfilUser_informacaoJogador.setOnClickListener {
            val intent = Intent(this, InformacaoJogadorActivity::class.java)
            startActivity(intent)
        }

        txtNomeJogador_informacaoJogador.text = Nome
        txtDataNascimento_informacaoJogador.text = DataNascimento
        txtNacionalidade_informacaoJogador.text = Nacionalidade
        txtNomeClube_informacaoJogador.text = nomeEquipa
    }
}