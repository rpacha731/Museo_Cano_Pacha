package com.tecno_moviles.museo_cano_pacha.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tecno_moviles.museo_cano_pacha.R

class LoginFragment : Fragment() {

    private lateinit var btnIngresar : Button
    private lateinit var txtOlvideContra : TextView
    private lateinit var editUsuario : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        btnIngresar = view.findViewById(R.id.btnIngresar)
        txtOlvideContra = view.findViewById(R.id.btnOlvideContra)
        editUsuario = view.findViewById(R.id.editUsuario)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnIngresar.setOnClickListener {

            var aux1 : Boolean
            var aux2 : Boolean
            if (MainLoginActivity.prefs.username!!.equals(editUsuario.text))

            Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToSplashExitoActivity())
        }

        txtOlvideContra.setOnClickListener {
            Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToRecuperarContrasenaFragment2())
        }
    }
}