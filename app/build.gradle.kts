plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.bakhdev.notesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bakhdev.notesapp"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures  {
        viewBinding = true
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    //dagger hilt
    implementation(libs.daggerHilt)
    annotationProcessor(libs.daggerHilt.compiler)

    //rx java
    implementation(libs.rx.android)
    implementation(libs.rx.java)

    //room
    implementation(libs.room.runtime)
    implementation(libs.room.rxjava)
    annotationProcessor(libs.room.compiler)

    //unit test
    testImplementation(libs.junit)

    //instrumental test
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}