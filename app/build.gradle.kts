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
    // ✅ Bloque para activar View Binding (Correcto)
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // --- Dependencias base (ya existentes) ---
    implementation(libs.appcompat)
    implementation(libs.material) // Usamos esta para la base de Material 3
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // --- Patrón MVVM (ViewModel y LiveData) ---
    // Eliminamos 'val lifecycle_version' ya que no se usa con libs.
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // --- Acceso a API (Retrofit y Gson) ---
    // Eliminamos 'val retrofit_version' ya que no se usa con libs.
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // --- Google Maps y Servicios de Localización (Requisito de Sensor) ---
    // Eliminamos las variables de versión redundantes.
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
}