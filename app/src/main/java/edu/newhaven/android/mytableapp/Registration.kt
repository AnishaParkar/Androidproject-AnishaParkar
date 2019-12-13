package edu.newhaven.android.mytableapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.R
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.signup_back_button
import kotlinx.android.synthetic.main.activity_registration.signup_email
import kotlinx.android.synthetic.main.activity_registration.signup_name
import kotlinx.android.synthetic.main.activity_registration.signup_phone
import kotlinx.android.synthetic.main.activity_registration.signup_submit
import org.jetbrains.anko.sdk27.coroutines.onClick

class Registration : GenericMethods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        signup_submit.setOnClickListener {
            performRegistration()
        }

        alreadyhaveaccount.setOnClickListener {
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        signup_back_button.onClick { finish() }
    }


    private fun performRegistration(){

        var email = signup_email.text.toString()
        var name = signup_name.text.toString()
        var password = signup_phone.text.toString()

        if(email.isEmpty() || password.isEmpty()|| name.isEmpty()){
            Toast.makeText(this,"Please enter your email and password",Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Login","Login id")
                    startActivity(Intent(this, SignIn::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign up failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
