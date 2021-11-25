package com.tecno_moviles.museo_cano_pacha.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecno_moviles.museo_cano_pacha.database.SharedPref
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityMainLoginBinding

class MainLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoginBinding

    companion object {
        lateinit var prefs: SharedPref
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPref(applicationContext)

        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}