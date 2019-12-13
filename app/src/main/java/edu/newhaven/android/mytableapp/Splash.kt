package edu.newhaven.android.mytableappv1

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import edu.newhaven.android.mytableapp.Registration
import edu.newhaven.android.mytableapp.SignIn
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class Splash : GenericMethods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this@Splash, R.layout.activity_splash)

        Dexter.withActivity(this@Splash)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (!report.areAllPermissionsGranted())
                    {
                        longToast("Please Grant All Permissions")
                        finish()
                    } else {
//                        if(auth.currentUser != null) {
//                            startHomeActivity(this@Splash)
//                        }

                        splash_signup.onClick {
                            startActivity<Registration>("text" to "Sign Up")
                        }

                        splash_login.onClick {
                            startActivity<Registration>("text" to "Login")
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {

                }
            }).check()
    }
}


