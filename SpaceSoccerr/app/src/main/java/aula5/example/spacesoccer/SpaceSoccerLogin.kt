package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SpaceSoccerLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_login)

        val buttontLogin = findViewById<Button>(R.id.btlogin)
        buttontLogin.setOnClickListener {
            val intent = Intent(this, SpaceSoccerTorneios:: class.java)
            startActivity(intent)
        }
    }
}
