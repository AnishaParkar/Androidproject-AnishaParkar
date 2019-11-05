package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        GenericMethods.setFullScreen(window)

        Login_button.setOnClickListener {

            val emailL = email_address_login.text.toString()
            val passwordL = Password_login.text.toString()


            Log.d("Login", "email is" + emailL)
            Log.d("Login", "password is: $passwordL")
        }

        Not_user.onClick {
            //startActivity<SignUp>()
            finish()

        }

    }
}
