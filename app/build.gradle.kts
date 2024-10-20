
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
    id ("com.google.firebase.firebase-perf")
}

android {

    namespace = "com.simple"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jcore.simple"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable  = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )


        }
        debug {
           // applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.appcompat)
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation
    implementation(libs.core)
    implementation(libs.kotlinx.serialization.core)
    implementation (libs.androidx.navigation.compose)

    //UI
    implementation(libs.ui)
    implementation(libs.icons)
    implementation(libs.illustrations)
    implementation(libs.compose.materialIconsExtended)

    // Retrofit for network requests
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.retrofit2.rxjava2.adapter)
    implementation ( libs.kotlinx.coroutines.android)
    implementation(libs.androidx.hilt.navigation.compose)


    // Image loading library
    implementation(libs.coil.compose)


    // Dagger - Hilt for dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt (libs.response.type.keeper)



    // Import the BoM for the Firebase platform
    implementation (platform(libs.firebase.bom))
    implementation (libs.google.firebase.auth.ktx)
    implementation (libs.play.services.auth)
    implementation(libs.firebase.analytics.ktx)
    implementation (libs.firebase.crashlytics.ktx)
    implementation (libs.firebase.perf.ktx)
    implementation (libs.play.services.ads)
    implementation (libs.firebase.messaging.ktx)
    implementation (libs.review.ktx)
    implementation(libs.app.update.ktx)

}
