package com.everis.coroutines.framework.location

import android.content.Context
import android.os.Looper
import com.everis.coroutines.data.location.LocationDataSource
import com.everis.coroutines.domain.CoroutineLocation
import com.everis.coroutines.framework.permission.PermissionManager
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationManager(
    private val context: Context,
    private val permissionManager: PermissionManager
) : LocationDataSource {
    override suspend fun getLastLocation(): CoroutineLocation =
        suspendCancellableCoroutine { continuation ->
            permissionManager.checkLocationPermission(
                failure = {
                    continuation.resume(CoroutineLocation())
                },
                success = {
                    LocationServices.getFusedLocationProviderClient(context).lastLocation
                        .addOnFailureListener {
                            continuation.resume(CoroutineLocation())
                        }
                        .addOnCanceledListener {
                            continuation.resume(CoroutineLocation())
                        }
                        .addOnSuccessListener { location ->
                            continuation.resume(
                                value = CoroutineLocation(
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )
                            )
                        }
                }
            )
        }

    private var callback: LocationCallback? = null

    override fun startLocation(result: (CoroutineLocation) -> Unit) {
        stopLocation()
        permissionManager.checkLocationPermission(
            failure = {
                result(CoroutineLocation())
            },
            success = {
                LocationServices.getFusedLocationProviderClient(context)
                    .requestLocationUpdates(
                        LocationRequest.create()
                            .setInterval(10000)
                            .setFastestInterval(5000)
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY),
                        object : LocationCallback() {
                            override fun onLocationResult(p0: LocationResult?) {
                                super.onLocationResult(p0)
                                p0?.lastLocation?.let { location ->
                                    result(
                                        CoroutineLocation(
                                            latitude = location.latitude,
                                            longitude = location.longitude
                                        )
                                    )
                                } ?: result(CoroutineLocation())
                            }
                        }.apply {
                            callback = this
                        },
                        Looper.getMainLooper()
                    )
            }
        )
    }

    override fun stopLocation() {
        callback?.let {
            LocationServices.getFusedLocationProviderClient(context)
                .removeLocationUpdates(callback)
        }
    }
}