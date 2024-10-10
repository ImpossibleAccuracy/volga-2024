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
    api(libs.spring.validation)
    developmentOnly(libs.spring.devtools)

    // SPRING CLOUD DEPENDENCIES
    api(libs.bundles.spring.cloud.client)
    implementation(libs.gson)

    // SWAGGER
    api(libs.spring.openapi.ui)
    api(libs.spring.openapi.api)

    // FEIGN
    api(libs.reactivefeign.webclient)
    api(libs.reactivefeign.cloud)
    api(libs.reactivefeign.configuration)

    // DATABASE
    implementation(libs.spring.r2dbc)

    // LOMBOK
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
