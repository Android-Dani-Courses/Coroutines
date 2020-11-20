package com.everis.coroutines.presentation.activities.main.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everis.coroutines.usecase.location.GetLastLocation
import com.everis.coroutines.usecase.location.GetLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocationViewModelProvider(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getLastLocation: GetLastLocation,
    private val getLocation: GetLocation,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationViewModel(
            dispatcher = dispatcher,
            getLastLocation = getLastLocation,
            getLocation = getLocation
        ) as T
    }
}