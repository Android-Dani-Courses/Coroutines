package com.everis.coroutines

import android.app.Application
import com.everis.coroutines.data.location.LocationRepository
import com.everis.coroutines.data.randomusers.RandomUsersRepository
import com.everis.coroutines.framework.location.LocationManager
import com.everis.coroutines.framework.permission.PermissionManager
import com.everis.coroutines.framework.users.RandomUsersManager
import com.everis.coroutines.framework.users.RandomUsersService
import com.everis.coroutines.presentation.navigation.Navigator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoroutinesApp : Application() {
    val navigator = Navigator(this)

    private val permissionManager by lazy { PermissionManager(context = applicationContext) }
    private val locationManager by lazy {
        LocationManager(context = applicationContext, permissionManager = permissionManager)
    }
    val locationRepository by lazy { LocationRepository(locationDataSource = locationManager) }

    private val randomUsersRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val randomUsersService by lazy { randomUsersRetrofit.create(RandomUsersService::class.java) }
    private val randomUsersManager by lazy {
        RandomUsersManager(service = randomUsersService)
    }
    val randomUsersRepository by lazy { RandomUsersRepository(dataSource = randomUsersManager) }
}