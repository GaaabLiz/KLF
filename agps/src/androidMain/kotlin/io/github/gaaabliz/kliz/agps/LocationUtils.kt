package io.github.gaaabliz.kliz.agps

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

object LocationUtils {

    // TODO : sistemare questo file con quello di upofood

    lateinit var fusedLocationClient: FusedLocationProviderClient

    fun calcolateMeterDistance(
        start : LatLng,
        end : LatLng
    ) : Float {
        val startLocation = Location("start")
        startLocation.latitude = start.latitude
        startLocation.longitude = start.longitude
        val endLocation = Location("end")
        endLocation.latitude = end.latitude
        endLocation.longitude = end.longitude
        return startLocation.distanceTo(endLocation)
    }

    fun calcolateMeterDistance(
        longitudeStart : Double,
        latitudeStart : Double,
        longitudeEnd : Double,
        latitudeEnd : Double
    ) : Float {
        val startLocation = Location("start")
        startLocation.latitude = latitudeStart
        startLocation.longitude = longitudeStart
        val endLocation = Location("end")
        endLocation.latitude = latitudeEnd
        endLocation.longitude = longitudeEnd
        return startLocation.distanceTo(endLocation)
    }

    fun initLocation(activity : Activity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
    }

    @SuppressLint("MissingPermission")
    fun getLastLocation(context: Context, onLoc : (LatLng) -> Unit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw IllegalStateException("Location permission not granted)")
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    /*val currentLocation = LatLng.newBuilder()
                        .setLatitude(location.latitude.toDouble())
                        .setLongitude(location.longitude.toDouble())
                        .build()*/
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    onLoc(currentLocation)
                } else {
                    throw IllegalStateException("Location is null")
                }
            }
            .addOnFailureListener{
                throw IllegalStateException("Error getting location" + it.message)
            }
    }

    fun genRandomLocation() : LatLng {
        /*return LatLng.newBuilder()
            .setLatitude(45.123456)
            .setLongitude(7.123456)
            .build()*/
        return LatLng(45.123456, 7.123456)
    }

}