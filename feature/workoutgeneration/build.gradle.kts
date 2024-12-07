plugins {
    alias(libs.plugins.fuelt.android.feature)
    alias(libs.plugins.fuelt.android.library.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

secrets {
    propertiesFileName = "secrets.properties"

    defaultPropertiesFileName = "local.defaults.properties"
}


android {
    namespace = "com.maritogram.fuelt.feature.workoutgeneration"

    buildFeatures.buildConfig = true
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")

}