package com.everis.coroutines.data.location

import com.everis.coroutines.domain.CoroutineLocation

class LocationRepository(private val locationDataSource: LocationDataSource) {
    suspend fun getLastLocation() = locationDataSource.getLastLocation()
    fun startLocation(result: (CoroutineLocation) -> Unit) = locationDataSource.startLocation(result)
    fun stopLocation() = locationDataSource.stopLocation()
}