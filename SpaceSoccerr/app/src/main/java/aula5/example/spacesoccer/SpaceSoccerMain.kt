package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SpaceSoccerMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_login_registar)

        val btLogin = findViewById<Button>(R.id.buttonLogin)
        btLogin.setOnClickListener {
            val intent = Intent(this, SpaceSoccerLogin :: class.java)
            startActivity(intent)
        }

        val btRegistar = findViewById<Button>(R.id.buttonRegister)
        btRegistar.setOnClickListener {
            val intent = Intent(this, SpaceSoccerRegistar :: class.java)
            startActivity(intent)
        }
    }
}
