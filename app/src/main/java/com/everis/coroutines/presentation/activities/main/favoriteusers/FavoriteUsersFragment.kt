package com.everis.coroutines.presentation.activities.main.favoriteusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.everis.coroutines.CoroutinesApp
import com.everis.coroutines.databinding.FragmentFavoriteUsersBinding
import com.everis.coroutines.usecase.users.GetFavoriteUsers
import com.everis.coroutines.usecase.users.RemoveFavoriteUser

class FavoriteUsersFragment : Fragment() {
    private var _binding: FragmentFavoriteUsersBinding? = null
    private val binding get() = _binding!!
    private val app by lazy { requireActivity().application as CoroutinesApp }
    private val randomUsersRepository by lazy { app.randomUsersRepository }
    private val viewModel by viewModels<FavoriteUsersViewModel> {
        FavoriteUsersViewModelProvider(
            getFavoriteUsers = GetFavoriteUsers(repository = randomUsersRepository),
            removeFavoriteUser = RemoveFavoriteUser(repository = randomUsersRepository),
        )
    }

    private lateinit var adapter: FavoriteUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteUsersAdapter(
            onUserClick = viewModel::onUserClick,
        )
        binding.favoriteUsersRv.setHasFixedSize(true)
        binding.favoriteUsersRv.adapter = adapter


        viewModel.favoriteUsersLD.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}