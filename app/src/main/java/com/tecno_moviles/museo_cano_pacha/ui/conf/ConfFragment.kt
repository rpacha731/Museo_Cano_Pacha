package com.tecno_moviles.museo_cano_pacha.ui.conf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentConfBinding

class ConfFragment : Fragment() {

    private lateinit var confViewModel: ConfViewModel
    private var _binding: FragmentConfBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        confViewModel =
            ViewModelProvider(this).get(ConfViewModel::class.java)

        _binding = FragmentConfBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSlideshow
        //confViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}