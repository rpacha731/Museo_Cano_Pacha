package com.tecno_moviles.museo_cano_pacha.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentPerfilBinding
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity

class PerfilFragment : Fragment() {

    private lateinit var perfilViewModel: PerfilViewModel
    private var _binding: FragmentPerfilBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        perfilViewModel =
            ViewModelProvider(this).get(PerfilViewModel::class.java)

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPerfilFav.setOnClickListener {
            Navigation.findNavController(view).navigate(PerfilFragmentDirections.actionNavPerfilToFavoritosFragment())
        }

        binding.nombrePerfl.setText(SplashActivity.prefs.username)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}