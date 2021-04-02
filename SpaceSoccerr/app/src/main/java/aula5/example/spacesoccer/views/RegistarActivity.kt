package aula5.example.spacesoccer.views

// << ---------------------------------------------------------------------------------------- >> //

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import aula5.example.spacesoccer.R
import aula5.example.spacesoccer.helper.VolleyHelper
import kotlinx.android.synthetic.main.registar.*

// << ---------------------------------------------------------------------------------------- >> //

class RegistarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registar)

        val numerocartaocidadao = findViewById<EditText>(R.id.textCCNumber_registar)
        val firstName = findViewById<EditText>(R.id.textFirstNameRegister_registar)
        val lastName = findViewById<EditText>(R.id.textLastNameRegister_registar)
        val birthDate = findViewById<EditText>(R.id.textBirthDate_registar)
        val username = findViewById<EditText>(R.id.textEmailRegister_registar)
        val password = findViewById<EditText>(R.id.textPasswordRegister_registar)
        val confPassword = findViewById<EditText>(R.id.textConfirmPasswordRegister_registar)

        val registerButton = findViewById<Button>(R.id.buttonRegister_registar)

        val check = findViewById<CheckBox>(R.id.checkBox)

            if (password.text.toString() == confPassword.text.toString()) {
                registerButton.setOnClickListener {
                    if(check.isChecked) {
                        VolleyHelper.instance.userRegister(
                            this@RegistarActivity,
                            numerocartaocidadao.text.toString(),
                            firstName.text.toString(),
                            lastName.text.toString(),
                            birthDate.text.toString(),
                            username.text.toString(),
                            password.text.toString()
                        ) {
                            if (it) {
                                val intent =
                                    Intent(this@RegistarActivity, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@RegistarActivity,
                                    this@RegistarActivity.getString(R.string.login_failed),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }else {
                        Toast.makeText(
                            this@RegistarActivity,
                            this@RegistarActivity.getString(R.string.checkBox_failed),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }else {
            Toast.makeText(
                this@RegistarActivity,
                this@RegistarActivity.getString(R.string.login_failed),
                Toast.LENGTH_LONG
            ).show()
        }
        textTermsOfUse.setOnClickListener {
            val intent = Intent(this, TermosECondicoes::class.java)
            startActivity(intent)
        }
    }
}