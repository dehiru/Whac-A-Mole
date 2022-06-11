package com.example.whac_a_mole.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.whac_a_mole.R
import com.example.whac_a_mole.data.GamePreferencesDataStore
import com.example.whac_a_mole.databinding.FragmentStartBinding
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() =_binding!!
    private lateinit var dataStore: GamePreferencesDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.playBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStore = GamePreferencesDataStore(requireContext())
         lifecycleScope.launch {
            val value = dataStore?.read(requireContext()) ?: 0
            binding.bestScoreValue.text = value.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}