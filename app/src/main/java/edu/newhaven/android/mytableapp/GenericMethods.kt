package edu.newhaven.android.mytableapp

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

class GenericMethods{

    companion object {

        //Setting the screen to fullscreen
        fun setFullScreen(window: Window)
        {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        fun hideSoftKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
        }

    }
}


