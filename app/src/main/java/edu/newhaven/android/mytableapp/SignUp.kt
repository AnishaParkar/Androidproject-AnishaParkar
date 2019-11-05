package edu.newhaven.android.mytableapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        GenericMethods.setFullScreen(window)

        profile_submit.setOnClickListener {
            performRegister()
        }
        already_user.onClick {
            //startActivity<PhoneNumber>()
            startActivity<Login>()

        }

    }

    private fun performRegister() {
        val name = first_name.text.toString()
        val email = email_address.text.toString()
        //val password = Password.text.toString()

        if (email.isEmpty() ) {
            Toast.makeText(this, "Please enter Email and Password", Toast.LENGTH_SHORT).show()
            return
        }
        else {
            startActivity<Home>()
        }


        Log.d("SignUp", "name is" + name)
        Log.d("SignEmail", "Email is: " + email)
        //Log.d("SignPassword", "Password is:  + $password")

//        //Firebase Authentication to create user with email and password
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email)
//            .addOnCompleteListener {
//                if (!it.isSuccessful) return@addOnCompleteListener
//
//                //else if successful
//
//                //saveUserDetailsToFirebase()
//                Log.d("Main", "Successfully created user with uid: ${it?.result?.user?.uid} ")
//
//                startActivity<Login>()
//            }
//            .addOnFailureListener {
//                Log.d("Main", "Failed to create user: ${it.message}")
//                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT)
//                    .show()
//
//            }
    }



}


