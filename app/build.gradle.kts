plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
}

val apiBaseUrl: String by project
val openweathermapApiKey: String by project

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.weatherapp"
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField(
            "String", "OPENWEATHERMAP_URL",
            "\"$apiBaseUrl\""
        )
        buildConfigField(
            "String", "OPENWEATHERMAP_API_KEY",
            "\"$openweathermapApiKey\""
        )

        useLibrary("android.test.runner")
        useLibrary("android.test.base")
        useLibrary("android.test.mock")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.viewpager2)

    implementation(Libs.coreCtx)

    implementation(Libs.viewModelKtx)
    implementation(Libs.lifecycleExt)
    implementation(Libs.lifecycleKtx)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Libs.lifecycleCompiler)

    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)

    implementation(Libs.material)
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    implementation(Libs.hilt)
    implementation(Libs.hiltJetpack)
    kapt(Libs.hiltCompiler)
    kapt(Libs.hiltJetpackCompiler)

    implementation(Libs.retrofit)
    implementation(Libs.gson)
    implementation(Libs.okhttp)
    implementation(Libs.loggingInterceptor)

    implementation(Libs.coil)

    implementation(Libs.circleIndicator)

    implementation(Libs.googleMaps)

    testImplementation(Libs.coroutinesCore)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockitoKotlin)
    testImplementation(Libs.coreTesting)
    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
    testImplementation(Libs.coroutinesTest)
    androidTestImplementation(Libs.testRunner)
    androidTestImplementation(Libs.espresso)
}