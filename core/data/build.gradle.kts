plugins {
    alias(libs.plugins.fuelt.android.library)
    alias(libs.plugins.fuelt.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.maritogram.fuelt.core.data"
}

dependencies {
    api(projects.core.database)
//    api(projects.core.datastore)



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}