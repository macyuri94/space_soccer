package aula5.example.spacesoccer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_space_soccer_ver_equipa.*

class SpaceSoccerVerEquipa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_ver_equipa)

        val buttonCriarEquipaa = findViewById<Button>(R.id.btcriarequipa)
        buttonCriarEquipaa.setOnClickListener {
            val intent = Intent(this, SpaceSoccerCriarEquipa:: class.java)
            startActivity(intent)
        }

        painel1.setOnClickListener {
            val intent = Intent(this, SpaceSoccerInformacaoEquipa:: class.java)
            startActivity(intent)
        }
    }
}
