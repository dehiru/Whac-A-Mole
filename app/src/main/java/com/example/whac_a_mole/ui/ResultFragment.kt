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
import com.example.whac_a_mole.databinding.FragmentResultBinding
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() =_binding!!
    private lateinit var dataStore: GamePreferencesDataStore
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            score = it.getInt("score")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.playAgainBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        binding.menuBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataStore = GamePreferencesDataStore(requireContext())
        lifecycleScope.launch {
            var bestScore = dataStore?.read(requireContext()) ?: 0
            if(bestScore < score) {
                bestScore = score
                dataStore?.save(bestScore, requireContext())
            }
            binding.bestScoreValue.text = bestScore.toString()
        }
        binding.scoreValue.text = score.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}