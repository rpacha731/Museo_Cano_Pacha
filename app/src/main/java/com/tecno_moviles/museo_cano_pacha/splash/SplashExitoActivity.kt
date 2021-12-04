package com.tecno_moviles.museo_cano_pacha.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecno_moviles.museo_cano_pacha.HomeActivity
import com.tecno_moviles.museo_cano_pacha.databinding.ActivitySplashExitoBinding
import java.util.*
import kotlin.concurrent.schedule

class SplashExitoActivity : AppCompatActivity() {

    var timer = Timer()
    private lateinit var binding: ActivitySplashExitoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashExitoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userTextViewSplash.text = SplashActivity.prefs.username

        timer.schedule(3000) {
            startActivity(Intent(this@SplashExitoActivity, HomeActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}