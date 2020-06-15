package aula5.example.spacesoccer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.helper.VolleyHelper

class RegistarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_soccer_registar)

        val username = findViewById<EditText>(R.id.textEmailRegister_registar)
        val password = findViewById<EditText>(R.id.textPasswordRegister_registar)
        val confPassword=findViewById<EditText>(R.id.textConfirmPasswordRegister_registar)

        val registerButton=findViewById<Button>(R.id.buttonRegister_registar)

        if (password.text.toString()==confPassword.text.toString()) {
            registerButton.setOnClickListener {
                VolleyHelper.instance.userRegister(
                    this@RegistarActivity,
                    username.text.toString(),
                    password.text.toString()
                ) {
                    if (it) {
                        val intent = Intent(this@RegistarActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@RegistarActivity,
                            this@RegistarActivity.getString(R.string.login_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }else {
            Toast.makeText(
                this@RegistarActivity,
                this@RegistarActivity.getString(R.string.login_failed),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
