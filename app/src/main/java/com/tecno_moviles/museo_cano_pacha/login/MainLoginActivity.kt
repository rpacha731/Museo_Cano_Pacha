package com.tecno_moviles.museo_cano_pacha.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityMainLoginBinding

class MainLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoginBinding

    private var btnIrRegistro : Button? = null
    private var btnIrLogin : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnIrLogin = supportFragmentManager.fragments.first().view?.findViewById(R.id.btnIrLogin)
        btnIrRegistro = supportFragmentManager.fragments.first().view?.findViewById(R.id.btnIrRegistrar)

        btnIrLogin?.setOnClickListener {
            Toast.makeText(baseContext, "holis", Toast.LENGTH_LONG).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentOpcLog, LoginFragment()).addToBackStack(null).commit()
        }

        btnIrRegistro?.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentOpcLog, RegistroFragment()).addToBackStack(null).commit()
        }

    }


}