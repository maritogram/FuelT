plugins {
    alias(libs.plugins.fuelt.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.fuelt.android.room)

}

android {
    namespace = "com.maritogram.fuelt.core.database"

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}