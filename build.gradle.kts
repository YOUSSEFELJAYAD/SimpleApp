// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id( "com.google.gms.google-services") version "4.4.2" apply false
    id ("com.google.firebase.crashlytics") version "3.0.2" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}
//RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())