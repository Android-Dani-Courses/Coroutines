package com.everis.coroutines.usecase.location

import com.everis.coroutines.data.location.LocationRepository
import com.everis.coroutines.domain.CoroutineLocation
import kotlinx.coroutines.delay

class GetLastLocation(private val repository: LocationRepository) {
    suspend operator fun invoke() = repository.getLastLocation()
}
