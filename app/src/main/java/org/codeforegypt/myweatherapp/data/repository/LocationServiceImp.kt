package org.codeforegypt.myweatherapp.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import org.codeforegypt.myweatherapp.domain.repository.LocationService
import kotlin.coroutines.resume


class LocationServiceImpl(private val context: Context) : LocationService {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Result<Pair<Double, Double>> {
        return try {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            // Check permissions using explicit Manifest.permission constants
            val hasFineLocationPermission = ContextCompat.checkSelfPermission(
                context,
                "android.permission.ACCESS_FINE_LOCATION"
            ) == PackageManager.PERMISSION_GRANTED

            val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
                context,
                "android.permission.ACCESS_COARSE_LOCATION"
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasFineLocationPermission && !hasCoarseLocationPermission) {
                return Result.failure(Exception("Location permissions not granted"))
            }

            // Get current location using suspendCancellableCoroutine
            suspendCancellableCoroutine { continuation ->
                // Try last known location first
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null && continuation.isActive) {
                            continuation.resume(Result.success(Pair(location.latitude, location.longitude)))
                        } else {
                            // Request new location
                            requestNewLocation(fusedLocationClient, continuation)
                        }
                    }
                    .addOnFailureListener { exception ->
                        if (continuation.isActive) {
                            continuation.resume(Result.failure(exception))
                        }
                    }
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocation(
        fusedLocationClient: FusedLocationProviderClient,
        continuation: CancellableContinuation<Result<Pair<Double, Double>>>
    ) {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000
        ).setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(5000)
            .setMaxUpdateDelayMillis(15000)
            .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation
                if (location != null && continuation.isActive) {
                    fusedLocationClient.removeLocationUpdates(this)
                    continuation.resume(Result.success(Pair(location.latitude, location.longitude)))
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        // Handle cancellation
        continuation.invokeOnCancellation {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}
