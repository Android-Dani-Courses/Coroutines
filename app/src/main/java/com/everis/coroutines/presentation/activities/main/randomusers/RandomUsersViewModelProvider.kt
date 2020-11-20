package com.everis.coroutines.presentation.activities.main.randomusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everis.coroutines.usecase.users.GetUsers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RandomUsersViewModelProvider(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getUsers: GetUsers,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RandomUsersViewModel(dispatcher = dispatcher, getUsers = getUsers) as T
    }
}