plugins {
    alias(libs.plugins.fuelt.android.feature)
    alias(libs.plugins.fuelt.android.library.compose)
}

android {
    namespace = "com.maritogram.fuelt.feature.home"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.junit.ktx)
}