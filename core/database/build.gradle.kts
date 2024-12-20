plugins {
    alias(libs.plugins.fuelt.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.fuelt.android.room)
    alias(libs.plugins.fuelt.hilt)
}

android {
    namespace = "com.maritogram.fuelt.core.database"

}

dependencies {
    api(projects.core.model)


    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}