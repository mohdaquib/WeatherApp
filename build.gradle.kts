// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.4.32")
    val secretGradlePlugin by extra("1.3.0")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.kotlinGradlePlugin)
        classpath(Libs.navigationGradlePlugin)
        classpath(Libs.hiltGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean"){
    delete(rootProject.buildDir)
}