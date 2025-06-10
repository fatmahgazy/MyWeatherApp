package org.codeforegypt.myweatherapp.domain.repository

interface LocationService {
    suspend fun getCurrentLocation(): Result<Pair<Double, Double>>
}