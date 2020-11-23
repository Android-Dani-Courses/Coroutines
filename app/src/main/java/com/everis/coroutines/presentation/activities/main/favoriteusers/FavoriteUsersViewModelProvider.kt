package com.everis.coroutines.presentation.activities.main.favoriteusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everis.coroutines.usecase.users.GetFavoriteUsers
import com.everis.coroutines.usecase.users.GetUsers
import com.everis.coroutines.usecase.users.RemoveFavoriteUser
import com.everis.coroutines.usecase.users.SaveFavoriteUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FavoriteUsersViewModelProvider(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getFavoriteUsers: GetFavoriteUsers,
    private val removeFavoriteUser: RemoveFavoriteUser,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteUsersViewModel(
            dispatcher = dispatcher,
            getFavoriteUsers = getFavoriteUsers,
            removeFavoriteUser = removeFavoriteUser,
        ) as T
    }
}