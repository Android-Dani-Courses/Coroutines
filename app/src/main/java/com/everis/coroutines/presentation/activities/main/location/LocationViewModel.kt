package com.everis.coroutines.presentation.activities.main.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.everis.coroutines.domain.CoroutineLocation
import com.everis.coroutines.presentation.activities.BaseViewModel
import com.everis.coroutines.usecase.location.GetLastLocation
import com.everis.coroutines.usecase.location.GetLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LocationViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getLastLocation: GetLastLocation,
    private val getLocation: GetLocation,
) : BaseViewModel(dispatcher = dispatcher) {
    private val _locationLD = MutableLiveData(CoroutineLocation())
    val locationLD: LiveData<CoroutineLocation>
        get() = _locationLD

    fun onGetLastLocationClick() {
        scope.cancel()
        getLocation.stop()
        scope.launch {
            val location = getLastLocation()
            _locationLD.postValue(location)
        }
    }

    fun onGetLocationClick() {
        scope.cancel()
        getLocation.stop()
        getLocation.start(result = { location ->
            _locationLD.postValue(location)
        })
    }

    override fun onCleared() {
        super.onCleared()
        getLocation.stop()
    }
}