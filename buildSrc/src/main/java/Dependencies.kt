object Versions {
    const val androidGradle = "4.0.0"
    const val kotlin = "1.3.70"
    const val constraintLayout = "2.0.0-beta4"
    const val coroutines = "1.3.2"
    const val support = "1.1.0"
    const val coreCtx = "1.1.0"
    const val navigation = "2.2.0"
    const val lifecycle = "2.2.0"
    const val testing = "2.1.0"
    const val dagger = "2.26"
    const val room = "2.2.3"
    const val retrofit = "2.7.1"
    const val moshi = "1.9.2"
    const val glide = "4.11.0"
    const val junit = "4.12"
    const val testRunner = "1.1.0"
    const val espresso = "3.1.1"
    const val material = "1.1.0-beta02"
    const val mockito = "3.2.4"
    const val mockitoKotlin = "2.1.0"
    const val truth = "1.0.1"
    const val viewpager2 = "1.0.0"
    const val circleIndicator = "2.1.4"
    const val hilt = "2.28-alpha"
    const val hiltJetpack = "1.0.0-alpha01"
}

object Libs {
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradle}"
    val navigationGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.support}"
    val coreCtx = "androidx.core:core-ktx:${Versions.coreCtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val viewpager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val material = "com.google.android.material:material:${Versions.material}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val junit = "junit:junit:${Versions.junit}"
    val testRunner = "androidx.test.ext:junit:${Versions.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val coreTesting = "androidx.arch.core:core-testing:${Versions.testing}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val circleIndicator = "me.relex:circleindicator:${Versions.circleIndicator}"
    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltJetpack = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpack}"
    val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpack}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}