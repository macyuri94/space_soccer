package aula5.example.spacesoccer.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper

class CriarIncidenciaActivity : AppCompatActivity() {

    var idJogo:         Int? = 0
    var idTorneio:      Int? = 0
    var nomeTorneio:    String? = null
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_estatistica)

        val bundle = intent.extras
        bundle?.let {
            idJogo      = it.getInt("IdJogo")
            idTorneio   = it.getInt("IdTorneio")
            nomeTorneio = it.getString("Nome")
            email = it.getString("Email")
        }

        val possebolaA      = findViewById<EditText>(R.id.txtTotalPosseDeBolaEquipaCasa_criarincidencia)
        val possebolaB      = findViewById<EditText>(R.id.txtTotalPosseDebolaEquipaConvidada_criarincidencia)
        val rematesbalizaA  = findViewById<EditText>(R.id.txtTotalRematesBalizaEquipaCasa_criarincidencia)
        val rematestotaisA  = findViewById<EditText>(R.id.txtTotalRematesEquipaCasa_criarincidencia)
        val rematesbalizaB  = findViewById<EditText>(R.id.txtTotalRematesBalizaEquipaConvidada_criarincidencia)
        val rematestotaisB  = findViewById<EditText>(R.id.txtTotalRematesEquipaConvidada_criarincidencia)
        val numincidenciasA = findViewById<EditText>(R.id.txtTotalFaltasEquipaCasa_criarincidencia)
        val numincidenciasB = findViewById<EditText>(R.id.txtTotalFaltasEquipaConvidada_criarincidencia)
        val golosA          = findViewById<EditText>(R.id.txtTotalGolosEquipaCasa_criarincidencia)
        val golosB          = findViewById<EditText>(R.id.txtTotalGolosEquipaConvidada_criarincidencia)
        val foradejogoA     = findViewById<EditText>(R.id.txtTotalForaDeJogoEquipaCasa_criarincidencia)
        val foradejogoB     = findViewById<EditText>(R.id.txtTotalForaDeJogoEquipaConvidada_criarincidencia)

        val btGuardarDados_criarIncidencia= findViewById<Button>(R.id.btGuardarDados_criarincidencia)

        btGuardarDados_criarIncidencia.setOnClickListener {
            VolleyHelper.instance.addEstatistica(
                this@CriarIncidenciaActivity,
                idJogo.toString().toInt(),
                idTorneio.toString().toInt(),
                possebolaA.text.toString().toInt(),
                possebolaB.text.toString().toInt(),
                rematesbalizaA.text.toString().toInt(),
                rematestotaisA.text.toString().toInt(),
                rematesbalizaB.text.toString().toInt(),
                rematestotaisB.text.toString().toInt(),
                numincidenciasA.text.toString().toInt(),
                numincidenciasB.text.toString().toInt(),
                golosA.text.toString().toInt(),
                golosB.text.toString().toInt(),
                foradejogoA.text.toString().toInt(),
                foradejogoB.text.toString().toInt()
            ) {
                if (it) {
                    val intent = Intent(this@CriarIncidenciaActivity, JogosActivity::class.java)
                    intent.putExtra("IdTorneio", idTorneio)
                    intent.putExtra("Email", email)
                    intent.putExtra("Nome", nomeTorneio)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@CriarIncidenciaActivity,
                        this@CriarIncidenciaActivity.getString(R.string.createStatistic_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}