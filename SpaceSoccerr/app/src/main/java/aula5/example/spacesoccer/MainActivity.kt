package aula5.example.spacesoccer

// << ---------------------------------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import aula5.example.spacesoccer.views.LoginActivity
import aula5.example.spacesoccer.views.RegistarActivity

// << ---------------------------------------------------------------------------------------------------------------- >> //

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_registar)

        val btLogin = findViewById<Button>(R.id.buttonLogin_login_registar)
        btLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btRegistar = findViewById<Button>(R.id.buttonRegister_login_registar)
        btRegistar.setOnClickListener {
            val intent = Intent(this, RegistarActivity::class.java)
            startActivity(intent)
        }
    }
}
