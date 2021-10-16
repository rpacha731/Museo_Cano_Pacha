package com.tecno_moviles.museo_cano_pacha.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tecno_moviles.museo_cano_pacha.R


class OpcionesLoginFragment : Fragment() {



    private lateinit var btnIrLogin : Button
    private lateinit var btnIrRegistro : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_opciones_login, container, false)

        btnIrLogin = view.findViewById(R.id.btnIrLogin)
        btnIrRegistro = view.findViewById(R.id.btnIrRegistrar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnIrLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(OpcionesLoginFragmentDirections.actionOpcionesLoginFragmentToLoginFragment())
        }

        btnIrRegistro.setOnClickListener {
            Navigation.findNavController(view).navigate(OpcionesLoginFragmentDirections.actionOpcionesLoginFragmentToRegistroFragment())
        }
    }



}