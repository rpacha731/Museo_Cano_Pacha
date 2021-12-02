package com.tecno_moviles.museo_cano_pacha.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.login.MainLoginActivity
import com.tecno_moviles.museo_cano_pacha.services.NetworkService
import java.util.*

import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    var timer = Timer()

    companion object {
        lateinit var prefs: SharedPref
        lateinit var networking: NetworkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prefs = SharedPref(applicationContext)
        networking = NetworkService()
        networking.initialize(applicationContext)

        timer.schedule(5000) {
            startActivity(Intent(this@SplashActivity, MainLoginActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}