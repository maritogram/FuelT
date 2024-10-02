plugins {
    alias(libs.plugins.fuelt.android.application.compose)
    alias(libs.plugins.fuelt.android.application)
    alias(libs.plugins.kotlin.serialization)
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
    implementation(projects.feature.trainer)

    // Core dependencies
    implementation(projects.core.designsystem)

    // External libraries dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.navigation.compose)

}