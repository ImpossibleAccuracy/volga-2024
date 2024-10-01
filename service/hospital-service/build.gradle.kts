plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spring)
    alias(libs.plugins.kotlin.allopen)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation(project(":service:shared"))

    // PLATFORM DEPENDENCIES
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib)

    // SPRING DEPENDENCIES
    implementation(libs.spring.web)
    implementation(libs.spring.actuator)
    developmentOnly(libs.spring.devtools)

    // SPRING CLOUD DEPENDENCIES
    implementation(libs.bundles.spring.cloud.client)
    implementation(libs.gson)

    // OTHER DEPENDENCIES
    implementation(libs.jackson.kotlin)
}
