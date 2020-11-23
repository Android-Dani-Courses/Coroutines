package com.everis.coroutines.presentation.activities.main.home

import com.everis.coroutines.presentation.activities.BaseViewModel
import com.everis.coroutines.presentation.navigation.Navigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val navigator: Navigator
) : BaseViewModel(dispatcher = dispatcher) {

    fun onCounterClick() {
        navigator.goTo(HomeFragmentDirections.actionHomeFragmentToCounterFragment())
    }

    fun onSumClick() {
        navigator.goTo(HomeFragmentDirections.actionHomeFragmentToSumFragment())
    }

    fun onLocationClick() {
        navigator.goTo(HomeFragmentDirections.actionHomeFragmentToLocationFragment())
    }

    fun onRandomAPIClick() {
        navigator.goTo(HomeFragmentDirections.actionHomeFragmentToRandomUsersFragment())
    }

    fun onFavoriteUsersClick() {
        navigator.goTo(HomeFragmentDirections.actionHomeFragmentToFavoriteUsersFragment())
    }

}