package com.tecno_moviles.museo_cano_pacha.ui.ayuda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentAyudaBinding

class AyudaFragment : Fragment() {

    private lateinit var ayudaViewModel: AyudaViewModel
    private var _binding: FragmentAyudaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ayudaViewModel =
            ViewModelProvider(this).get(AyudaViewModel::class.java)

        _binding = FragmentAyudaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}