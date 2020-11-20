package com.everis.coroutines.presentation.activities.main.randomusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everis.coroutines.domain.User
import com.everis.coroutines.presentation.activities.BaseViewModel
import com.everis.coroutines.usecase.users.GetUsers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomUsersViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getUsers: GetUsers
) : BaseViewModel(dispatcher = dispatcher) {
    private val _usersLD = MutableLiveData<List<User>>()
    val usersLD: LiveData<List<User>>
        get() = _usersLD

    init {
        scope.launch {
            _usersLD.postValue(getUsers())
        }
    }
}