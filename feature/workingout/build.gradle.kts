plugins {
    alias(libs.plugins.fuelt.android.feature)
    alias(libs.plugins.fuelt.android.library.compose)
}

android {
    namespace = "com.maritogram.fuelt.feature.workingout"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.junit.ktx)
    implementation(libs.material)
}