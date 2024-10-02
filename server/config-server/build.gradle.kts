plugins {
    id("application")
    id("java-library")
    alias(libs.plugins.spring)
}

dependencies {
    // SPRING DEPENDENCIES
    implementation(libs.spring.actuator)
    implementation(libs.spring.security)
    developmentOnly(libs.spring.devtools)

    // SPRING CLOUD DEPENDENCIES
    implementation(libs.spring.cloud.config.server)
}