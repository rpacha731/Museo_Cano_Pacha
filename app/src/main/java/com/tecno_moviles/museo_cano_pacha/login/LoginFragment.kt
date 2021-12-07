package com.tecno_moviles.museo_cano_pacha.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity

class LoginFragment : Fragment() {

    private lateinit var btnIngresar: Button
    private lateinit var txtOlvideContra: TextView
    private lateinit var editUsuario: EditText
    private lateinit var editPassword: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        btnIngresar = view.findViewById(R.id.btnIngresar)
        txtOlvideContra = view.findViewById(R.id.btnOlvideContra)
        editUsuario = view.findViewById(R.id.editUsuario)
        editPassword = view.findViewById(R.id.editPassword2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnIngresar.setOnClickListener {

            val userTmp = MainLoginActivity.baseDatos.getUser(editUsuario.text.toString())

            if (editUsuario.text.toString() == "") {
                Toast.makeText(context, "Por favor, ingrese un pinche usuario", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (userTmp == null) {
                Toast.makeText(context, "El usuario no se encuentra registrado", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (editPassword.text.toString() == "") {
                Toast.makeText(context, "Por favor, ingrese la pinche password", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (userTmp.password != editPassword.text.toString()) {
                Toast.makeText(context, "La password es incorrecta", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (userTmp.password == editPassword.text.toString()) {
                SplashActivity.prefs.username = userTmp.username
                SplashActivity.prefs.userEmail = userTmp.email
                Navigation.findNavController(view)
                    .navigate(LoginFragmentDirections.actionLoginFragmentToSplashExitoActivity())
            }

        }

        txtOlvideContra.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(LoginFragmentDirections.actionLoginFragmentToRecuperarContrasenaFragment2())
        }
    }
}