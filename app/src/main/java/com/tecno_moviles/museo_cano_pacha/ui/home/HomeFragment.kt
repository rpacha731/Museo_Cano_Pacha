package com.tecno_moviles.museo_cano_pacha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.zxing.integration.android.IntentIntegrator
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentHomeBinding
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFavoritos.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToFavoritosFragment())
        }
        binding.btnEscanear.setOnClickListener {
            IntentIntegrator(activity).initiateScan()
        }
        binding.btnVerListado.setOnClickListener {
            Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToListadoFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}