package com.everis.coroutines.presentation.activities.main.favoriteusers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everis.coroutines.domain.User
import com.everis.coroutines.presentation.activities.BaseViewModel
import com.everis.coroutines.usecase.users.GetFavoriteUsers
import com.everis.coroutines.usecase.users.RemoveFavoriteUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FavoriteUsersViewModel(
    dispatcher: CoroutineDispatcher,
    private val getFavoriteUsers: GetFavoriteUsers,
    private val removeFavoriteUser: RemoveFavoriteUser,
) : BaseViewModel(dispatcher = dispatcher) {
    private val _favoriteUsersLD = MutableLiveData<List<User>>()
    val favoriteUsersLD: LiveData<List<User>>
        get() = _favoriteUsersLD

    init {
        scope.launch {
            _favoriteUsersLD.postValue(getFavoriteUsers())
        }
    }

    fun onUserClick(user: User) {
        scope.launch {
            _favoriteUsersLD.postValue(removeFavoriteUser(user))
        }
    }
}