plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.compose.compiler)
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.wharfstreetstudiostask1"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.wharfstreetstudiostask1"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.version.code.get().toInt()
        versionName = libs.versions.version.name.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

   /* buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }*/

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
    }
}

dependencies {

    // Material design dependency
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.androidx.camera.core)
    implementation(libs.room.common.jvm)
    implementation(libs.androidx.room.ktx.v281)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.navigation.common.ktx)
    kapt(libs.hilt.android.compiler)

    // Hilt
    implementation(libs.hilt.android.v247)
    kapt(libs.hilt.android.compiler.v247)

    // For ViewModel injection
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    kapt(libs.androidx.hilt.compiler)

    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler.v281)
}


