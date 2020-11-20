package com.everis.coroutines.presentation.activities.main.randomusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.everis.coroutines.CoroutinesApp
import com.everis.coroutines.databinding.FragmentRandomUsersBinding
import com.everis.coroutines.usecase.users.GetUsers

class RandomUsersFragment : Fragment() {
    private var _binding: FragmentRandomUsersBinding? = null
    private val binding get() = _binding!!
    private val app by lazy { requireActivity().application as CoroutinesApp }
    private val randomUsersRepository by lazy { app.randomUsersRepository }
    private val viewModel by viewModels<RandomUsersViewModel> {
        RandomUsersViewModelProvider(getUsers = GetUsers(repository = randomUsersRepository))
    }

    private lateinit var adapter: RandomUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RandomUsersAdapter()
        binding.usersRv.setHasFixedSize(true)
        binding.usersRv.adapter = adapter

        viewModel.usersLD.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}