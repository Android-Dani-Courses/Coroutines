package com.everis.coroutines.presentation.activities.main.randomusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everis.coroutines.domain.User
import com.everis.coroutines.presentation.activities.BaseViewModel
import com.everis.coroutines.usecase.users.GetFavoriteUsers
import com.everis.coroutines.usecase.users.GetUsers
import com.everis.coroutines.usecase.users.RemoveFavoriteUser
import com.everis.coroutines.usecase.users.SaveFavoriteUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomUsersViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getUsers: GetUsers,
    private val getFavoriteUsers: GetFavoriteUsers,
    private val saveFavoriteUser: SaveFavoriteUser,
    private val removeFavoriteUser: RemoveFavoriteUser,
) : BaseViewModel(dispatcher = dispatcher) {
    private val _usersLD = MutableLiveData<List<User>>()
    val usersLD: LiveData<List<User>>
        get() = _usersLD
    private val _favoriteUsersLD = MutableLiveData<List<User>>()
    val favoriteUsersLD: LiveData<List<User>>
        get() = _favoriteUsersLD

    private var favoriteUsers: List<User> = emptyList()
        set(value) {
            field = value
            _favoriteUsersLD.postValue(field)
        }

    init {
        scope.launch {
            launch {
                _usersLD.postValue(getUsers())
            }
            launch {
                favoriteUsers = getFavoriteUsers()
            }
        }
    }

    fun onUserClick(user: User) {
        scope.launch {
            favoriteUsers = if (favoriteUsers.contains(user)) {
                removeFavoriteUser(user = user)
            } else {
                saveFavoriteUser(user = user)
            }
        }
    }
}