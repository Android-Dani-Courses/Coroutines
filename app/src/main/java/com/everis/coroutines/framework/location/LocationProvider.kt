package com.everis.coroutines.framework.location

import android.content.Context
import com.everis.coroutines.data.location.LocationDataSource
import com.everis.coroutines.framework.permission.PermissionDataSourceAndroid

class LocationProvider private constructor(private val context: Context) {
    private val permissionDataSource: PermissionDataSourceAndroid
        get() = PermissionDataSourceAndroid(context = context)

    val provideLocationDataSource: LocationDataSource
        get() = LocationDataSourceAndroid(
            context = context,
            permissionDataSource = permissionDataSource
        )

    companion object {
        private lateinit var instance: LocationProvider
        fun getInstance(context: Context) = if (!::instance.isInitialized) {
            LocationProvider(context = context.applicationContext).apply {
                instance = this
            }
        } else {
            instance
        }
    }
}