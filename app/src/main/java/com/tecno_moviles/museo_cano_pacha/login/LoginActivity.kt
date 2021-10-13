package com.tecno_moviles.museo_cano_pacha.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityLoginBinding
import com.tecno_moviles.museo_cano_pacha.splash.SplashExitoActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            startActivity(Intent(this, SplashExitoActivity::class.java))
        }
    }
}