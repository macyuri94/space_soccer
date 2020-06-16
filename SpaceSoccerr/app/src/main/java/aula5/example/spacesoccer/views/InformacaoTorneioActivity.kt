package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.informacao_torneio.*

// << ---------------------------------------------------------------------------------------- >> //

class InformacaoTorneioActivity : AppCompatActivity() {

    var idTorneio: Int? = null
    var nomeTorneio: String? = null
    var dtInicio: String? = null
    var dtFim: String? = null
    var NumEquipas: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.informacao_torneio)

        val bundle = intent.extras
        bundle?.let {
            idTorneio = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            dtInicio = it.getString("dtInicio")
            dtFim = it.getString("dtFim")
            NumEquipas = it.getInt("NumEquipas")
        }

        txtTituloTorneio_informacaoTorneio.text = nomeTorneio
        txtNomeTorneio2_informacaoTorneio.text = nomeTorneio
        txtDataInicio2_informacaoTorneio.text = dtInicio
        txtDataFim2_informacaoTorneio.text = dtFim
        txtNumeroEquipas2_informacaoTorneio.text = NumEquipas.toString()
    }
}