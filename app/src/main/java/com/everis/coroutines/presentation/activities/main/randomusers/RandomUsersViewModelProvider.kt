package com.everis.coroutines.presentation.activities.main.randomusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everis.coroutines.usecase.users.GetFavoriteUsers
import com.everis.coroutines.usecase.users.GetUsers
import com.everis.coroutines.usecase.users.RemoveFavoriteUser
import com.everis.coroutines.usecase.users.SaveFavoriteUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RandomUsersViewModelProvider(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getUsers: GetUsers,
    private val getFavoriteUsers: GetFavoriteUsers,
    private val saveFavoriteUser: SaveFavoriteUser,
    private val removeFavoriteUser: RemoveFavoriteUser,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RandomUsersViewModel(
            dispatcher = dispatcher,
            getUsers = getUsers,
            getFavoriteUsers = getFavoriteUsers,
            saveFavoriteUser = saveFavoriteUser,
            removeFavoriteUser = removeFavoriteUser,
        ) as T
    }
}