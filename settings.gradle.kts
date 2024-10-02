pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SimbirApp2024 (expecting kotlin ban)"

include(
    ":server:config-server",
    ":server:discovery-server"
)

include(
    ":service:shared",
    ":service:auth-service",
//    ":service:hospital-service"
)
