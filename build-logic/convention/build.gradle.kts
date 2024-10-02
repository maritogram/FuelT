import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// Enable writing scripts in kotlin, don't know why we don't define it in my app level setting script
plugins {
    `kotlin-dsl`
}

// Acts as a namespace for my convention plugins, I think.
group = "com.maritogram.fuelt.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

// Not sure what this does yet.
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    implementation(libs.truth)
}

// Add a task to validate plugins basically, version and stuff I believe
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

println("Hey")

// Registering custom plugins
gradlePlugin{
    plugins {
        register("androidApplicationCompose") {
            id = "fuelt.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "fuelt.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "fuelt.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "fuelt.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "fuelt.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }


    }

}

