package com.example.whac_a_mole.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.whac_a_mole.R
import com.example.whac_a_mole.databinding.FragmentGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val GAME_LENGTH_SECONDS = 30
private const val UPDATE_FREQUENCY_MS: Long = 500

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() =_binding!!
    private var activeCell: Int = 0
    private var score: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.cell1.setOnClickListener {
            checkCellIsActive(1)
        }
        binding.cell2.setOnClickListener {
            checkCellIsActive(2)
        }
        binding.cell3.setOnClickListener {
            checkCellIsActive(3)
        }
        binding.cell4.setOnClickListener {
            checkCellIsActive(4)
        }
        binding.cell5.setOnClickListener {
            checkCellIsActive(5)
        }
        binding.cell6.setOnClickListener {
            checkCellIsActive(6)
        }
        binding.cell7.setOnClickListener {
            checkCellIsActive(7)
        }
        binding.cell8.setOnClickListener {
            checkCellIsActive(8)
        }
        binding.cell9.setOnClickListener {
            checkCellIsActive(9)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scoreValue.text = score.toString()
        lifecycleScope.launch {
            var timeLeft = GAME_LENGTH_SECONDS
            while(true) {
                binding.timerValue.text = timeLeft.toString()
                repeat(1000 / UPDATE_FREQUENCY_MS.toInt()) {
                    chooseActiveCell()
                }
                timeLeft--
                if(timeLeft == 0) break
            }
            val action = GameFragmentDirections.actionGameFragmentToResultFragment(score = score)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkCellIsActive(cellNum: Int) {
        if(cellNum == activeCell) {
            setImageToStandard(cellNum)
            activeCell = 0
            score++
            binding.scoreValue.text = score.toString()
        }
    }

    private suspend fun chooseActiveCell() {
        activeCell = (1..9).random()
        setImageToActive(activeCell)
        delay(UPDATE_FREQUENCY_MS)
        setImageToStandard(activeCell)
    }

    private fun setImageToStandard(cellNum: Int) {
        when(cellNum) {
            1 -> binding.cell1.setImageResource(R.drawable.game_cell)
            2 -> binding.cell2.setImageResource(R.drawable.game_cell)
            3 -> binding.cell3.setImageResource(R.drawable.game_cell)
            4 -> binding.cell4.setImageResource(R.drawable.game_cell)
            5 -> binding.cell5.setImageResource(R.drawable.game_cell)
            6 -> binding.cell6.setImageResource(R.drawable.game_cell)
            7 -> binding.cell7.setImageResource(R.drawable.game_cell)
            8 -> binding.cell8.setImageResource(R.drawable.game_cell)
            9 -> binding.cell9.setImageResource(R.drawable.game_cell)
        }
    }

    private fun setImageToActive(cellNum: Int) {
        when(cellNum) {
            1 -> binding.cell1.setImageResource(R.drawable.mole_on_cell)
            2 -> binding.cell2.setImageResource(R.drawable.mole_on_cell)
            3 -> binding.cell3.setImageResource(R.drawable.mole_on_cell)
            4 -> binding.cell4.setImageResource(R.drawable.mole_on_cell)
            5 -> binding.cell5.setImageResource(R.drawable.mole_on_cell)
            6 -> binding.cell6.setImageResource(R.drawable.mole_on_cell)
            7 -> binding.cell7.setImageResource(R.drawable.mole_on_cell)
            8 -> binding.cell8.setImageResource(R.drawable.mole_on_cell)
            9 -> binding.cell9.setImageResource(R.drawable.mole_on_cell)
        }
    }
}