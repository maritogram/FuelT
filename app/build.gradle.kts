plugins {
    alias(libs.plugins.fuelt.android.application.compose)
    alias(libs.plugins.fuelt.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.fuelt.hilt)
}

android {
    namespace = "com.maritogram.fuelt"

    defaultConfig {
        applicationId = "com.maritogram.fuelt"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Feature dependencies
    implementation(projects.feature.home)
    implementation(projects.feature.routines)
    implementation(projects.feature.workoutgeneration)
    implementation(projects.feature.workingout)

    // Core dependencies
    implementation(projects.core.designsystem)


    ksp(libs.hilt.compiler)


    // External libraries dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)

}