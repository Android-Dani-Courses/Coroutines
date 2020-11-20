package com.everis.coroutines.data.location

import com.everis.coroutines.domain.CoroutineLocation

interface LocationDataSource {
    suspend fun getLastLocation(): CoroutineLocation
    fun startLocation(result: (CoroutineLocation) -> Unit)
    fun stopLocation()
}