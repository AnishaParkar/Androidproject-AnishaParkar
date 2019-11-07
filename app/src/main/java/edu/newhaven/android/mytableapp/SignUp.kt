package edu.newhaven.android.mytableapp


import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignUp : GenericMethods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this@SignUp, R.layout.activity_sign_up)

        signup_back_button.onClick { finish() }

        val email = intent.getStringExtra("email")!!
        val name = intent.getStringExtra("name")!!
        val type = intent.getStringExtra("type")!!
        val password = intent.getStringExtra("password")!!

        signup_email.text = email
        signup_name.setText(name)

        signup_submit.onClick {
            pd.setLabel("Please Wait")
            pd.show()
            val name = signup_name.text.toString()
            val phone = signup_phone.text.toString()

            when {
                name.isEmpty() -> longToast("Please enter your Name")
                phone.isEmpty() -> longToast("Please enter your Phone Number")
                phone.length != 10 -> longToast("Please enter valid Phone Number")
                else -> {
                    val db = database.getReference("Users")
                    db.addListenerForSingleValueEvent(object: ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {
                            pd.dismiss()
                        }

                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val pos = dataSnapshot.children.count()+1
                            info(pos)
                            val map = HashMap<String, String>()
                            map["name"] = name
                            map["phone"] = phone
                            map["email"] = email
                            db.child("User$pos").push()
                            db.child("User$pos").setValue(map)
                        }
                    })

                    if (type == "normallogin") {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this@SignUp) { task ->
                                if (task.isSuccessful) {
                                    pd.dismiss()
                                    longToast("User Registered Successfully")
                                    startHomeActivity(this@SignUp)
                                } else {
                                    pd.dismiss()
                                    longToast("Sign Up Failed - ${task.exception.toString()}")
                                }
                            }
                    } else {
                        pd.dismiss()
                        startHomeActivity(this@SignUp)
                    }
                }
            }
        }
    }



}


