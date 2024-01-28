package com.example.ftechnology

import Versions

object Libs {

    // AndroidX Core
    object Core {
        val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        val material = "com.google.android.material:material:${Versions.material}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
        val junit = "junit:junit:${Versions.junit}"
        val testExtJUnit = "androidx.test.ext:junit:${Versions.testExtJUnit}"
        val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }

    // Navigation
    object Navigation {
        val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    // Retrofit
    object Network {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    // OkHttp
    object OkHttp {
        val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    // Room Db
    object RoomDb {
        val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }

    // Dagger - Hilt
    object DaggerHilt {
        val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        val hiltNavigationFragment = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigationFragment}"
    }

    // Coroutines
    object Coroutines {
        val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesCore}"
        val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutinesAndroid}"
    }

    // Coroutine Lifecycle Scopes
    object CoroutineLifecycleScopes {
        val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
    }

    // FireBase
    object FireBase{
        val fireBase = "com.google.firebase:firebase-bom:${Versions.fireBaseBom}"
        val fireBaseAuth = "com.google.firebase:firebase-auth-ktx:${Versions.fireBaseAuth}"
    }

    // Lottie
    object Lottie {
        val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    }

    // Glide
    object Glide {
        val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    }
}