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
    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(project(":feature:workoutgeneration"))
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation(project(":feature:home"))


}