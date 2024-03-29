import com.example.ftechnology.Libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = Config.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(Libs.Core.coreKtx)
    implementation(Libs.Core.appcompat)
    implementation(Libs.Core.material)
    implementation(Libs.Core.constraintLayout)
    implementation(Libs.Core.swipeRefreshLayout)
    implementation("androidx.preference:preference:1.2.1")
    testImplementation(Libs.Core.junit)
    androidTestImplementation(Libs.Core.testExtJUnit)
    androidTestImplementation(Libs.Core.espressoCore)

    // Navigation
    implementation(Libs.Navigation.navigationFragmentKtx)
    implementation(Libs.Navigation.navigationUiKtx)

    // Retrofit
    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.converterGson)

    // OkHttp
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.loggingInterceptor)

    // Room Db
    implementation(Libs.RoomDb.roomRuntime)
    annotationProcessor(Libs.RoomDb.roomCompiler)
    kapt(Libs.RoomDb.roomCompiler)
    implementation(Libs.RoomDb.roomKtx)

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.4.2")

    // Coroutines
    implementation(Libs.Coroutines.kotlinxCoroutinesCore)
    implementation(Libs.Coroutines.kotlinxCoroutinesAndroid)

    // Coroutine Lifecycle Scopes
    implementation(Libs.CoroutineLifecycleScopes.lifecycleViewModelKtx)
    implementation(Libs.CoroutineLifecycleScopes.lifecycleRuntimeKtx)
    implementation(Libs.CoroutineLifecycleScopes.activityKtx)

    // FireBase
    implementation(platform(Libs.FireBase.fireBase))
    implementation(Libs.FireBase.fireBaseAuth)

    // Lottie
    implementation(Libs.Lottie.lottie)

    // Glide
    implementation(Libs.Glide.glide)
}

kapt {
    correctErrorTypes=true
}