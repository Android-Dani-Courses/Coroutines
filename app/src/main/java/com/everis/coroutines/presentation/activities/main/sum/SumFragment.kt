package com.everis.coroutines.presentation.activities.main.sum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.everis.coroutines.databinding.FragmentSumBinding

class SumFragment : Fragment() {
    private var _binding: FragmentSumBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SumViewModel> {
        SumViewModelProvider()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.firstNumberEt.addTextChangedListener { editable ->
            editable?.toString()?.let { value ->
                viewModel.onFirstNumberChange(value = value)
            }
        }
        binding.secondNumberEt.addTextChangedListener { editable ->
            editable?.toString()?.let { value ->
                viewModel.onSecondNumberChange(value = value)
            }
        }
        binding.calculateOperationBtn.setOnClickListener {
            viewModel.onCalculateClick()
        }

        viewModel.resultLD.observe(viewLifecycleOwner) { value ->
            binding.resultTv.text = value
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}