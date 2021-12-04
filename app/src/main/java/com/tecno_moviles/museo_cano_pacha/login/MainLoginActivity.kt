package com.tecno_moviles.museo_cano_pacha.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityMainLoginBinding

class MainLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoginBinding

    companion object {

        lateinit var baseDatos: BaseDatos

        fun verifyPassword(password: String): Boolean {
            for (c in password) {
                // Si no está entre a y z, ni entre A y Z, ni es un espacio
                if (!(c in 'a'..'z' || c in 'A'..'Z' || c == ' ')) {
                    return true
                }
            }
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseDatos = BaseDatos.getInstance(applicationContext)!!

        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}