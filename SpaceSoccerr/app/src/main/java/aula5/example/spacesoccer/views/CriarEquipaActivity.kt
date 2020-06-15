package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------------------------------- >> //

<<<<<<< HEAD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.*
>>>>>>> aa29a41cfe72eed4f1986efeb88abd56ccf5c0ba

// << ---------------------------------------------------------------------------------------------------------------- >> //

class CriarEquipaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_equipa)

<<<<<<< HEAD
        val nome = findViewById<EditText>(R.id.txtNomeEquipa_criarEquipa)
        val president = findViewById<EditText>(R.id.txtPresidente_criarEquipa)
        val coach = findViewById<EditText>(R.id.txtTreinador_criarEquipa)
        val yearfundation = findViewById<EditText>(R.id.txtAnoFundacao_criarEquipa)
        val cityfundation = findViewById<EditText>(R.id.txtCidadeFundacao_criarEquipa)

        val btGuardDado = findViewById<Button>(R.id.btGuardarDados_criarEquipa)

        btGuardDado.setOnClickListener {
            VolleyHelper.instance.addTeams(
                this@CriarEquipaActivity,
                nome.text.toString(),
                president.text.toString(),
                coach.text.toString(),
                yearfundation.text.toString(),
                cityfundation.text.toString()
            ) {
                if (it) {
                    val intent = Intent(this@CriarEquipaActivity, VerEquipaActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@CriarEquipaActivity,
                        this@CriarEquipaActivity.getString(R.string.creatTeam_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
=======
>>>>>>> aa29a41cfe72eed4f1986efeb88abd56ccf5c0ba

    }
}
