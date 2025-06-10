package org.codeforegypt.myweatherapp.di.appModule

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.codeforegypt.myweatherapp.data.repository.WeatherRepositoryImpl
import org.codeforegypt.myweatherapp.domain.repository.WeatherRepository
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }


    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }


    viewModel {
        WeatherViewModel(get())
    }
}