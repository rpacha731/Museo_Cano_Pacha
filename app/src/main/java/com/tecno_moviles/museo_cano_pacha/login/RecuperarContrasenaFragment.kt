package com.tecno_moviles.museo_cano_pacha.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tecno_moviles.museo_cano_pacha.R

class RecuperarContrasenaFragment : Fragment() {

    private lateinit var btnVolverLogin: View
    private lateinit var btnRecuperar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recuperar_contrasena, container, false)

        btnVolverLogin = view.findViewById(R.id.btnVolverLogin)
        btnRecuperar = view.findViewById(R.id.btnRecuperar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVolverLogin.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(RecuperarContrasenaFragmentDirections.actionRecuperarContrasenaFragment2ToOpcionesLoginFragment())
        }

        btnRecuperar.setOnClickListener {
            // Consumo de API para recuperar la contraseña (que llegue al mail o algo así)
            Toast.makeText(
                context,
                "Se ha enviado un correo a la dirección ingresada con la nueva contraseña",
                Toast.LENGTH_LONG
            ).show()
            Navigation.findNavController(view)
                .navigate(RecuperarContrasenaFragmentDirections.actionRecuperarContrasenaFragment2ToOpcionesLoginFragment())
        }

    }
}