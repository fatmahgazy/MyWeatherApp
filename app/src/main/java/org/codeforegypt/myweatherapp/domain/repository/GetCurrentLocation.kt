package org.codeforegypt.myweatherapp.domain.repository

import android.location.Location

interface LocationService {
    suspend fun getCurrentLocation(): Location?
    suspend fun getCityName(location: Location?): String?
}