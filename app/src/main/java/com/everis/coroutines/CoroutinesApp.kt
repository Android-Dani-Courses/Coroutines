package com.everis.coroutines

import android.app.Application
import com.everis.coroutines.data.location.LocationRepository
import com.everis.coroutines.data.randomusers.RandomUsersRepository
import com.everis.coroutines.framework.location.LocationProvider
import com.everis.coroutines.framework.users.di.RandomUsersProvider
import com.everis.coroutines.presentation.navigation.Navigator

class CoroutinesApp : Application() {
    val navigator = Navigator(this)

    private val locationProvider: LocationProvider
        get() = LocationProvider.getInstance(context = applicationContext)
    val locationRepository by lazy {
        LocationRepository(locationDataSource = locationProvider.provideLocationDataSource)
    }

    private val randomUsersProvider: RandomUsersProvider
        get() = RandomUsersProvider.getInstance(context = applicationContext)
    val randomUsersRepository by lazy {
        RandomUsersRepository(
            remoteDataSource = randomUsersProvider.provideRemoteDataSource,
            localDataSource = randomUsersProvider.provideLocalDataSource,
        )
    }
}