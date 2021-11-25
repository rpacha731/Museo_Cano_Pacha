package com.tecno_moviles.museo_cano_pacha.ui.cerrar_sesion

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentCerrarSesionBinding
import com.tecno_moviles.museo_cano_pacha.login.MainLoginActivity
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity
import java.util.*
import kotlin.concurrent.schedule

class CerrarSesionFragment : Fragment() {

    private lateinit var sesionViewModel: CerrarSesionViewModel
    private var _binding: FragmentCerrarSesionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sesionViewModel =
            ViewModelProvider(this).get(CerrarSesionViewModel::class.java)

        _binding = FragmentCerrarSesionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timer().schedule(2000) {
            SplashActivity.prefs.username = null
            activity?.finish()
            startActivity(Intent(activity?.applicationContext, MainLoginActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}