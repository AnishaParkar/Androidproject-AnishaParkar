package edu.newhaven.android.mytableapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.Home
import edu.newhaven.android.mytableappv1.R
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignIn : GenericMethods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        login_submit.setOnClickListener {
            doLogin()
        }

        newuser.setOnClickListener {
            val intent = Intent(this,Registration::class.java)
            startActivity(intent)
        }

        login_back_button.onClick { finish() }
    }

    fun doLogin(){
        var email = signup_email.text.toString()
        var name = signup_name.text.toString()
        var password = signup_phone.text.toString()

        if(email.isEmpty() || password.isEmpty()|| name.isEmpty()){
            Toast.makeText(this,"Please enter your email and password", Toast.LENGTH_SHORT).show()



            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
            if(currentUser!= null){
                startActivity(Intent(this,Home::class.java))
            }
            else{
                Toast.makeText(baseContext,"LoginFailed",Toast.LENGTH_SHORT).show()
            }

    }
}
