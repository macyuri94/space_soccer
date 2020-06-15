package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper

// << ---------------------------------------------------------------------------------------------------------------- >> //

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val username = findViewById<EditText>(R.id.txtEmail_login)
        val Password = findViewById<EditText>(R.id.txtPassword_login)
        val loginButton = findViewById<Button>(R.id.btLogin_login)


        loginButton.setOnClickListener {
            VolleyHelper.instance.userLogin ( this@LoginActivity,
                username.text.toString(),
                Password.text.toString()
            ) {
                if (it) {
                    val intent = Intent(this@LoginActivity, TorneiosActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        this@LoginActivity.getString(R.string.login_failed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
