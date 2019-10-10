package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_phone_number.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class PhoneNumber : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)

        back_button.onClick {
            finish()
        }

        phone_number1.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length == 10)
                    GenericMethods.hideSoftKeyboard(this@PhoneNumber)
            }

        })

        phone_submit.onClick {
            val cc = "+"+country_code.selectedCountryCodeAsInt
            val pn = phone_number1.text.toString()
            Log.d("Phone Number",cc+pn)

            when {
                pn.isEmpty() -> longToast("Please input your Phone Number")
                pn.length!=10 -> longToast("Invalid Phone Number")
                else -> {
                    startActivity<OTPVertification>("phone" to cc+pn)
                }
            }
        }
    }
}



