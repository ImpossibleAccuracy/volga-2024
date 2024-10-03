plugins {
    id("application")
    id("java-library")
    alias(libs.plugins.spring)
}

dependencies {
    implementation(project(":service:shared"))

    // SPRING DEPENDENCIES
    implementation(libs.spring.web)
    implementation(libs.spring.actuator)
    developmentOnly(libs.spring.devtools)

    // SPRING CLOUD DEPENDENCIES
    implementation(libs.bundles.spring.cloud.client)
    implementation(libs.gson)

    // LOMBOK
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // OTHER DEPENDENCIES
    // implementation(libs.jackson.kotlin)
}
