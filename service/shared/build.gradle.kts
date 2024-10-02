plugins {
    id("application")
    id("java-library")
    alias(libs.plugins.spring)
}

dependencies {
    // SPRING DEPENDENCIES
    api(libs.spring.web)
    api(libs.spring.actuator)
    developmentOnly(libs.spring.devtools)

    // SPRING CLOUD DEPENDENCIES
    api(libs.spring.cloud.openfeign)
    api(libs.bundles.spring.cloud.client)
    implementation(libs.gson)

    // LOMBOK
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // OTHER DEPENDENCIES
    api(libs.rxjava)
}
