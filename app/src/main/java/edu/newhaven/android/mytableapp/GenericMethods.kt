package edu.newhaven.android.mytableapp

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger

open class GenericMethods: AppCompatActivity(), AnkoLogger{

    companion object {

        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        //Setting the screen to fullscreen
        fun setFullScreen(window: Window) = window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        fun hideSoftKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
        }

        fun checkEmail(email:String, password:String):String {
            val emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            return if (email == "" || password == "")
                "Please input all the details"
            else if (!email.matches(emailpattern.toRegex()))
                "Invalid Email"
            else if (password.length < 6)
                "Password should be atleast 6 characters"
            else
                "Checked"
        }

//        fun googth
    }
}



