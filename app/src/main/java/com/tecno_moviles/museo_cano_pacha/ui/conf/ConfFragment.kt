package com.tecno_moviles.museo_cano_pacha.ui.conf

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos
import com.tecno_moviles.museo_cano_pacha.database.SharedPref
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentConfBinding
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
        binding.switch1.setOnClickListener {
            binding.switch1.isChecked = !binding.switch1.isChecked
        }

        val baseDatos = BaseDatos.getInstance(context)
        val user = baseDatos!!.getUser(SplashActivity.prefs.username.toString())

        //binding.inputNombre.hint = user!!.name
        binding.inputUsername.hint = SplashActivity.prefs.username
        binding.inputPassword.hint = user!!.password


        binding.edit1.setOnClickListener {
            if (binding.inputNombre.isEnabled) {
                binding.inputNombre.isEnabled = false
                binding.inputNombre.setHintTextColor(Color.GRAY)
            } else {
                binding.inputNombre.isEnabled = true
                //binding.inputNombre.hint = user!!.name
                binding.inputNombre.setHintTextColor(Color.BLACK)
            }
        }

        binding.edit2.setOnClickListener {
            if (binding.inputUsername.isEnabled) {
                binding.inputUsername.isEnabled = false
                binding.inputUsername.setHintTextColor(Color.GRAY)
            } else {
                binding.inputUsername.isEnabled = true
                binding.inputUsername.hint = SplashActivity.prefs.username
                binding.inputUsername.setHintTextColor(Color.BLACK)
            }
        }

        binding.edit3.setOnClickListener {
            if (binding.inputPassword.isEnabled) {
                binding.inputPassword.isEnabled = false
                binding.inputPassword.setHintTextColor(Color.GRAY)
            } else {
                binding.inputPassword.isEnabled = true
                binding.inputPassword.hint = user!!.password
                binding.inputPassword.setHintTextColor(Color.BLACK)
            }
        }

        binding.btnGuardarConf.setOnClickListener {
            binding.inputNombre.isEnabled = false
            binding.inputUsername.isEnabled = false
            binding.inputPassword.isEnabled = false

            Navigation.findNavController(view).navigate(ConfFragmentDirections.actionNavConfToNavHome())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}