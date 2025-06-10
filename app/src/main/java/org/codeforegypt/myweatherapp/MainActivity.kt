package org.codeforegypt.myweatherapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val current = LocalContext.current
            val viewModel: WeatherViewModel = koinViewModel()
            val uiState by viewModel.weatherState.collectAsState()
            Toast.makeText(current, "${uiState}", Toast.LENGTH_LONG).show()
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWeatherAppTheme {
    }
    }
}
//fun main() =
//    runBlocking {
//        val client = HttpClient(CIO) {
//            install(ContentNegotiation) {
//                json(Json {
//                    ignoreUnknownKeys = true
//                })
//            }
//        }
//
//        val useCase = GetWeatherUseCase(client)
//        val result = useCase.fetchWeather(30.0, 31.0)
//        println(result)
//    }