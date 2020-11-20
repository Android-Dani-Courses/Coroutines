package com.everis.coroutines.presentation.activities.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everis.coroutines.presentation.navigation.Navigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeViewModelProvider(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val navigator: Navigator
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(dispatcher = dispatcher, navigator = navigator) as T
    }
}