plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.emergenciapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.emergenciapp"
        minSdk = 24
        targetSdk = 36
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // --- Patrón MVVM (ViewModel y LiveData) ---
    val lifecycle_version = "2.7.0"
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

// --- Acceso a API (Retrofit y Gson) ---
    val retrofit_version = "2.9.0"
    implementation(libs.retrofit)
// Convertidor JSON a Objetos Java
    implementation(libs.converter.gson)

// --- Material 3 (Requisito de diseño) ---
    implementation(libs.material.v1110)

    val maps_version = "18.1.0"
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location) // Para el sensor GPS
}