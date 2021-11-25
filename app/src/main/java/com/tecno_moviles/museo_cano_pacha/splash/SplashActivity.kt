package com.tecno_moviles.museo_cano_pacha.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.database.SharedPref
import com.tecno_moviles.museo_cano_pacha.login.MainLoginActivity
import java.util.*

import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    var timer = Timer()

    companion object {
        lateinit var prefs: SharedPref
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prefs = SharedPref(applicationContext)

        timer.schedule(5000) {
            if (prefs.username == null || prefs.username == "") {
                startActivity(Intent(this@SplashActivity, MainLoginActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, SplashExitoActivity::class.java))
            }

            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}