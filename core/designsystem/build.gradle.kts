plugins {
    alias(libs.plugins.fuelt.android.library)
    alias(libs.plugins.fuelt.android.library.compose)
}

android {
    namespace = "com.maritogram.fuelt.core.designsystem"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material3)

}