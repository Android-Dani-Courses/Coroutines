package com.everis.coroutines.presentation.activities.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.everis.coroutines.CoroutinesApp
import com.everis.coroutines.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val app by lazy { requireActivity().application as CoroutinesApp }
    private val navigator by lazy { app.navigator }
    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelProvider(navigator = navigator)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.counterBtn.setOnClickListener {
            viewModel.onCounterClick()
        }
        binding.sumBtn.setOnClickListener {
            viewModel.onSumClick()
        }
        binding.locationBtn.setOnClickListener {
            viewModel.onLocationClick()
        }
        binding.randomApiBtn.setOnClickListener {
            viewModel.onRandomAPIClick()
        }
        binding.favoriteUesrsBtn.setOnClickListener {
            viewModel.onFavoriteUsersClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}