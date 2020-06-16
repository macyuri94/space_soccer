package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.estatisticas.*

// << ---------------------------------------------------------------------------------------- >> //

class EstatisticasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estatisticas)

        bthome_estatisticas.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        txtnometorneio_estatisticas.text = "Torneio A"

        txtdatajogo_estatisticas.text = "24 Maio"

        txttempofinal_estatisticas.text = "TF"

        txtnomequipa1_estatisticas.text = "Manchester United"
        txtnomequipa2_estatisticas.text = "Manchester City"

        txtresultadoequipa1_estatisticas.text = "2"
        txtresultadoequipa2_estatisticas.text = "0"

        txtpossedebolaequipa1_estatisticas.text = "60%"
        txtpossedebolaequipa2_estatisticas.text = "40%"

        txttotalrematesequipa1_estatisticas.text = "8"
        txttotalrematesequipa2_estatisticas.text = "5"

        txttotalrematesabalizaequipa1_estatisticas.text = "4"
        txttotalrematesabalizaequipa2_estatisticas.text = "3"

        txtcantosequipa1_estatisticas.text = "5"
        txtcantosequipa2_estatisticas.text = "6"

        txtforasdejogoequipa1_estatisticas.text = "10"
        txtforasdejogoequipa2_estatisticas.text = "12"

        txtincidenciasequipa1_estatisticas.text = "2"
        txtincidenciasequipa2_estatisticas.text = "2"
    }
}