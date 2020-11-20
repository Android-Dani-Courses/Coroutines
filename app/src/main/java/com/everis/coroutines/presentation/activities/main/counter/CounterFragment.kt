package com.everis.coroutines.presentation.activities.main.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.everis.coroutines.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CounterViewModel> {
        CounterViewModelProvider()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.intCounterThrowBtn.setOnClickListener {
            viewModel.onIntCounterThrowExceptionClick()
        }
        binding.intCounterStopBtn.setOnClickListener {
            viewModel.onIntCounterStopClick()
        }
        binding.doubleCounterThrowBtn.setOnClickListener {
            viewModel.onDoubleCounterThrowExceptionClick()
        }
        binding.doubleCounterStopBtn.setOnClickListener {
            viewModel.onDoubleCounterStopClick()
        }
        binding.startBtn.setOnClickListener {
            viewModel.onStartClick()
        }
        binding.stopBtn.setOnClickListener {
            viewModel.onStopClick()
        }

        viewModel.intCounterLD.observe(viewLifecycleOwner) { value ->
            binding.intCounterTv.text = value.toString()
        }
        viewModel.doubleCounterLD.observe(viewLifecycleOwner) { value ->
            binding.doubleCounterTv.text = String.format("%.1f", value)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}