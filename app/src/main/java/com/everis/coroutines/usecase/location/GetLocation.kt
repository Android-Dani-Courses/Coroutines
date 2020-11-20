package com.everis.coroutines.usecase.location

import com.everis.coroutines.data.location.LocationRepository
import com.everis.coroutines.domain.CoroutineLocation
import kotlinx.coroutines.delay

class GetLocation(private val repository: LocationRepository) {
    fun start(result: (CoroutineLocation) -> Unit) = repository.startLocation(result)
    fun stop() = repository.stopLocation()
}
