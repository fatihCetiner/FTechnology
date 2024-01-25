import com.example.ftechnology.Libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
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
    implementation(Libs.DaggerHilt.hiltAndroid)
    kapt(Libs.DaggerHilt.hiltAndroidCompiler)
    implementation(Libs.DaggerHilt.hiltNavigationFragment)

    // Coroutines
    implementation(Libs.Coroutines.kotlinxCoroutinesCore)
    implementation(Libs.Coroutines.kotlinxCoroutinesAndroid)

    // Coroutine Lifecycle Scopes
    implementation(Libs.CoroutineLifecycleScopes.lifecycleViewModelKtx)
    implementation(Libs.CoroutineLifecycleScopes.lifecycleRuntimeKtx)
    implementation(Libs.CoroutineLifecycleScopes.activityKtx)

    // Lottie
    implementation(Libs.Lottie.lottie)

    // Glide
    implementation(Libs.Glide.glide)
}

kapt {
    correctErrorTypes = true
}