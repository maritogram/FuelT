plugins {
    alias(libs.plugins.fuelt.android.library)
    alias(libs.plugins.fuelt.android.library.compose)
}
android {
    namespace = "com.maritogram.fuelt.ui"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}