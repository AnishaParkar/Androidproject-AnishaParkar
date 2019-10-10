package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_verification.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class OTPVertification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        val phone_number = intent.getStringExtra("phone")

        back_button.onClick {
            finish()
        }

        code_1.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1)
                    code_2.setSelection(0)
            }

        })

        code_2.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1)
                    code_3.setSelection(0)
            }

        })

        code_3.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1)
                    code_4.setSelection(0)
            }

        })

        code_4.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1)
                    code_5.setSelection(0)
            }

        })

        code_5.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1)
                    code_6.setSelection(0)
            }

        })

        code_6.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count == 1)
                    GenericMethods.hideSoftKeyboard(this@OTPVertification)
            }

        })

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phone_number!!,
            60,
            TimeUnit.SECONDS,
            this@OTPVertification,
            object:PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    val code = p0.smsCode
                    Log.d("SMS Code",code!!)
                    code_1.setText(code[0].toString())
                    code_2.setText(code[1].toString())
                    code_3.setText(code[2].toString())
                    code_4.setText(code[3].toString())
                    code_5.setText(code[4].toString())
                    code_6.setText(code[5].toString())
                    
                    FirebaseAuth.getInstance().signInWithCredential(p0).addOnCompleteListener { task ->
                        if(task.isSuccessful)
                            startActivity<Home>()
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d("verify failed", p0.message!!)
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    Log.d("code sent", p0)
                }
            })
    }
}



