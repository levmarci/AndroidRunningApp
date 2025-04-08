package android.runningapp.util.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrentLocationProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation(onResult: (LatLng?) -> Unit) {
        if (LocationPermissionManager(context).hasLocationPermission()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    onResult(location?.let { LatLng(it.latitude, it.longitude) })
                }
                .addOnFailureListener {
                    onResult(null)
                }
        }
        else {
            onResult(null)
        }
    }
}