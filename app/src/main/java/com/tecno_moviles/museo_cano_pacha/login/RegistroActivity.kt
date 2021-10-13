package com.tecno_moviles.museo_cano_pacha.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityRegistroBinding
import com.tecno_moviles.museo_cano_pacha.splash.SplashExitoActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {
            startActivity(Intent(this, SplashExitoActivity::class.java))
        }
    }
}