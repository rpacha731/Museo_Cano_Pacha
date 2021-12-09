package com.tecno_moviles.museo_cano_pacha.ui.conf

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tecno_moviles.museo_cano_pacha.HomeActivity
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentConfBinding
import com.tecno_moviles.museo_cano_pacha.login.MainLoginActivity
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity

class ConfFragment : Fragment() {

    private lateinit var confViewModel: ConfViewModel
    private var _binding: FragmentConfBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        confViewModel =
            ViewModelProvider(this).get(ConfViewModel::class.java)

        _binding = FragmentConfBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.switch1.setOnClickListener {
        //    binding.switch1.isChecked = !binding.switch1.isChecked
        //}

        val baseDatos = BaseDatos.getInstance(context)
        val user = baseDatos!!.getUser(SplashActivity.prefs.username.toString())

        binding.inputNombre.setText(user!!.nombre)
        binding.inputUsername.setText(user.username)
        binding.inputPassword2.setText(user.password)
        binding.inputMail.setText(user.email)
        binding.inputNombre.setTextColor(Color.GRAY)
        binding.inputUsername.setTextColor(Color.GRAY)
        binding.inputPassword2.setTextColor(Color.GRAY)
        binding.inputMail.setTextColor(Color.GRAY)

        binding.edit1.setOnClickListener {
            if (binding.inputNombre.isEnabled) {
                binding.inputNombre.isEnabled = false
                binding.inputNombre.setTextColor(Color.GRAY)
            } else {
                binding.inputNombre.isEnabled = true
                binding.inputNombre.setTextColor(Color.BLACK)
            }
        }

        binding.edit2.setOnClickListener {
            if (binding.inputUsername.isEnabled) {
                binding.inputUsername.isEnabled = false
                binding.inputUsername.setTextColor(Color.GRAY)
            } else {
                binding.inputUsername.isEnabled = true
                binding.inputUsername.setTextColor(Color.BLACK)
            }
        }

        binding.edit3.setOnClickListener {
            if (binding.inputPassword2.isEnabled) {
                binding.inputPassword2.isEnabled = false
                binding.inputPassword2.setTextColor(Color.GRAY)
            } else {
                binding.inputPassword2.isEnabled = true
                binding.inputPassword2.setTextColor(Color.BLACK)
            }
        }

        binding.edit4.setOnClickListener {
            if (binding.inputMail.isEnabled) {
                binding.inputMail.isEnabled = false
                binding.inputMail.setTextColor(Color.GRAY)
            } else {
                binding.inputMail.isEnabled = true
                binding.inputMail.setTextColor(Color.BLACK)
            }
        }

        binding.btnGuardarConf.setOnClickListener {
            binding.inputNombre.isEnabled = false
            binding.inputUsername.isEnabled = false
            binding.inputPassword2.isEnabled = false
            binding.inputMail.isEnabled = false
            binding.inputNombre.setTextColor(Color.GRAY)
            binding.inputUsername.setTextColor(Color.GRAY)
            binding.inputPassword2.setTextColor(Color.GRAY)
            binding.inputMail.setTextColor(Color.GRAY)

            if (binding.inputUsername.text.length > 10 || binding.inputUsername.text.length < 3) {
                Toast.makeText(
                    context,
                    "El nombre de usuario debe tener menos de 10 y más de 3 caracteres",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            if (binding.inputPassword2.text!!.length > 15 || binding.inputPassword2.text!!.length <= 7) {
                Toast.makeText(
                    context,
                    "La password debe tener menos de 15 y más de 8 caracteres",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                if (!MainLoginActivity.verifyPassword(binding.inputPassword2.text.toString())) {
                    Toast.makeText(
                        context,
                        "La password debe tener al menos un numero o caracter especial",
                        Toast.LENGTH_LONG
                    ).show()
                    return@setOnClickListener
                }
            }

            val usernameOld = SplashActivity.prefs.username
            SplashActivity.prefs.username = binding.inputUsername.text.toString()
            user.username = binding.inputUsername.text.toString()

            user.password = binding.inputPassword2.text.toString()

            user.nombre = binding.inputNombre.text.toString()

            user.email = binding.inputMail.text.toString()

            baseDatos.updateUser(user, usernameOld!!)

            HomeActivity.updateDrawer()

            Toast.makeText(context, "Se guardaron los datos correctamente", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(view).navigate(ConfFragmentDirections.actionNavConfToNavHome())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}