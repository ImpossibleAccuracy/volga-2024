plugins {
    id("application")
    id("java-library")
    alias(libs.plugins.spring)
}

dependencies {
    // SPRING
    api(libs.spring.web)
    api(libs.spring.security)
    api(libs.spring.actuator)
    developmentOnly(libs.spring.devtools)

    // SPRING CLOUD DEPENDENCIES
    api(libs.bundles.spring.cloud.client)
    implementation(libs.gson)

    // FEIGN
    api(libs.reactivefeign.webclient)
    api(libs.reactivefeign.cloud)
    api(libs.reactivefeign.configuration)

    // LOMBOK
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
