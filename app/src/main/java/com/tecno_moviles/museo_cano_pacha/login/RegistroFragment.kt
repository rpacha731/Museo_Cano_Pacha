package com.tecno_moviles.museo_cano_pacha.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity

class RegistroFragment : Fragment() {

    private lateinit var btnRegistro : Button
    private lateinit var editRegisUsuario : EditText
    private lateinit var editRegisPassword : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registro, container, false)

        btnRegistro = view.findViewById(R.id.btnRegistrar)
        editRegisUsuario = view.findViewById(R.id.editRegisUsuario)
        editRegisPassword = view.findViewById(R.id.editRegisPassword)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegistro.setOnClickListener {

            if (editRegisUsuario.text.length > 10 || editRegisUsuario.text.length < 3) {
                Toast.makeText(context, "El nombre de usuario debe tener menos de 10 y más de 3 caracteres", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (editRegisPassword.text.length > 15 || editRegisPassword.text.length <= 7) {
                Toast.makeText(context, "La password debe tener menos de 15 y más de 8 caracteres", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                if (!MainLoginActivity.verifyPassword(editRegisPassword.text.toString())) {
                    Toast.makeText(context, "La password debe tener al menos un numero o caracter especial", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            MainLoginActivity.baseDatos.registerUser(editRegisUsuario.text.toString(), editRegisPassword.text.toString())
            SplashActivity.prefs.username = editRegisUsuario.text.toString()

            Navigation.findNavController(view).navigate(RegistroFragmentDirections.actionRegistroFragmentToSplashExitoActivity())
        }
    }
}