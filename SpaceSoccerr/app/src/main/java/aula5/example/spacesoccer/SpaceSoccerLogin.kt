package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import aula5.example.spacesoccer.R
import kotlinx.android.synthetic.main.activity_space_soccer_login.*

class SpaceSoccerLogin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_login)

        btlogin.setOnClickListener {
            if(txtemail.text.toString().equals("nuno@gmail.com") && txtpassword.text.toString().equals("nuno")){
                val intent = Intent(this, SpaceSoccerTorneios:: class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, R.string.toast_msg_login,Toast.LENGTH_SHORT).show()
            }
        }
    }
}
