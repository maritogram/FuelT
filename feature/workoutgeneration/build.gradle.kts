plugins {
    alias(libs.plugins.fuelt.android.feature)
    alias(libs.plugins.fuelt.android.library.compose)
}

android {
    namespace = "com.maritogram.fuelt.feature.workoutgeneration"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

}