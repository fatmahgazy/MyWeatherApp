plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "org.codeforegypt.myweatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.codeforegypt.myweatherapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose.v182)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // Ktor for networking
    implementation(libs.ktor.client.core.v2311)
    implementation(libs.ktor.client.android.v2311) // Or ktor-client-cio
    implementation(libs.ktor.client.content.negotiation.v2311)
    implementation(libs.ktor.serialization.kotlinx.json.v2311)

    implementation(libs.koin.android.v356)
    implementation(libs.koin.androidx.compose.v356) //
    // Location services
    implementation(libs.play.services.location.v2101)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json.v162)

    // ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Permissions
    implementation(libs.accompanist.permissions)

    implementation(libs.kotlinx.coroutines.play.services)

    implementation (libs.androidx.animation)
    implementation (libs.androidx.material)
    implementation (libs.androidx.ui.v160)

    }
