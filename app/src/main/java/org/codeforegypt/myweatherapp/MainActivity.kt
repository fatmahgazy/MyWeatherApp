package org.codeforegypt.myweatherapp


import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.codeforegypt.myweatherapp.presentation.screen.WeatherScreen
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.koin.android.ext.android.getKoin
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {
    val viewModel: WeatherViewModel = getKoin().get<WeatherViewModel>()
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted =
            permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted =
            permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (fineLocationGranted && coarseLocationGranted) {
            onLocationPermissionGranted()
        } else {
            onLocationPermissionDenied()
        }
    }

    private fun requestLocationPermissions() {
        val hasPermission = listOf(
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED,
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED,
        ).all { it }

        if (!hasPermission) {
            locationPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        } else {
            onLocationPermissionGranted()
        }
    }

    private fun onLocationPermissionGranted() {
        // Your action when permission is granted
        Toast.makeText(this, "Location permission granted!", Toast.LENGTH_SHORT).show()
       viewModel.fetchWeather()
        // Example actions you might want to perform:
        // startLocationUpdates()
        // initializeLocationServices()
        // enableLocationFeatures()
    }

    private fun onLocationPermissionDenied() {
        // Handle permission denial
        Toast.makeText(this, "Location permission is required", Toast.LENGTH_LONG).show()

        // You might want to:
        // showPermissionExplanationDialog()
        // disableLocationFeatures()
        // redirectToAppSettings()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestLocationPermissions()
        setContent {
            val current = LocalContext.current
            val activity = current as? Activity
            val LOCATION_PERMISSION_REQUEST_CODE = 1001
            if (ContextCompat.checkSelfPermission(current, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            ) {
                if (activity != null) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE
                    )
                }
            }
            WeatherScreen(viewModel)
        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    }
}
