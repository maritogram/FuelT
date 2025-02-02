// Defines the location of plugins.
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Defines the dependency resolution strategies.
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Project name
rootProject.name = "Fuelt"

// Typesafe project accessors - easier way to declare dependencies on modules
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Adding subprojects.
include(":app")
include(":core:designsystem")
include(":feature:home")
include(":feature:routines")
include(":core:ui")
include(":core:database")
include(":core:data")
include(":core:model")
include(":feature:workoutgeneration")
include(":feature:workingout")
