package edu.newhaven.android.mytableappv1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class Login : GenericMethods() {

    val RC_SIGN_IN = 9001
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this@Login, R.layout.activity_login)

        login_title.text = intent.getStringExtra("text")

        login_back_button.onClick { finish() }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("655172341960-64euep8632ga7734pusfvl79ad4jo51d.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        login_submit.onClick {
            pd.setLabel("Please Wait")
            pd.show()
            val email = login_email.text.toString()
            val password = login_password.text.toString()
            val status = checkEmailPassword(email, password)
            if(status == "Checked") {
                checkEmail(email, "", "normallogin", password)
            } else longToast(status)
        }

        login_google.onClick {
            pd.setLabel("Loading")
            pd.show()
            startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
        }
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                fireBaseAuth(account!!)
            } catch (e: ApiException) {
                info("Google sign in failed - $e")
                pd.dismiss()
                e.printStackTrace()
            }
        }
    }

    fun fireBaseAuth(account: GoogleSignInAccount) {
        info("firebaseAuthWithGoogle:" + account.id!!)

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this@Login) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val email = user!!.email!!
                    val name = user.displayName!!
                    checkEmail(email, name, "googlelogin", "")
                } else {
                    pd.dismiss()
                    info("signInWithCredential:failure - ${task.exception}")
                    longToast("Authentication Failed")
                }
            }
    }

    fun checkEmail(email: String, name: String, type: String, password: String) {
        val db = database.getReference("Users")
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                pd.dismiss()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var status =  true
                for (d in dataSnapshot.children) {
                    if(d.child("Email").value == email) {
                        if (type == "normallogin") {
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this@Login) { task ->
                                    if (task.isSuccessful) {
                                        status = false
                                        pd.dismiss()
                                        startHomeActivity(this@Login)
                                    } else {
                                        status = false
                                        pd.dismiss()
                                        longToast("Login Failed - ${task.exception.toString()}")
                                    }
                                }
                        } else {
                            status = false
                            pd.dismiss()
                            startHomeActivity(this@Login)
                        }
                    }
                }
                if (status) {
                    pd.dismiss()
                    startActivity<SignUp>(
                        "email" to email,
                        "name" to name,
                        "type" to type,
                        "password" to password
                    )
                }
            }
        })
    }
}