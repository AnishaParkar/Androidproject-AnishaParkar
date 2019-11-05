package edu.newhaven.android.mytableapp

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick

class Login : GenericMethods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_submit.onClick {
            val email = login_email.text.toString()
            val password = login_password.text.toString()
            val status = checkEmail(email, password)
            if(status == "Checked") {

            } else longToast(status)
        }

        login_google.onClick {

        }
    }
}