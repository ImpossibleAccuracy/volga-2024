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
    implementation(libs.spring.cloud.eureka.server)
    implementation(libs.spring.cloud.config.client)
    implementation(libs.spring.cloud.bootstrap)
    implementation(libs.spring.aop)
    implementation(libs.spring.retry)
    implementation(libs.gson)
}