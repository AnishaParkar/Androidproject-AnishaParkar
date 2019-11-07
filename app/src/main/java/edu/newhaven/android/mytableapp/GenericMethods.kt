package edu.newhaven.android.mytableapp

import android.app.Activity
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.kaopiz.kprogresshud.KProgressHUD
import org.jetbrains.anko.*


open class GenericMethods: AppCompatActivity(), AnkoLogger{

    companion object {

        lateinit var auth: FirebaseAuth
        lateinit var database: FirebaseDatabase
        lateinit var pd:KProgressHUD


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
        fun setProgressDialog(context: Context):KProgressHUD {
            return KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f).setBackgroundColor(R.color.colorPrimaryDark)
        }

        fun startHomeActivity(activity: Activity) {
            activity.startActivity(activity.intentFor<Home>().newTask().clearTask())
            activity.finish()
        }

    }
}



