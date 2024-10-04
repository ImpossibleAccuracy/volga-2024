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
    implementation(libs.netty.core)
    implementation(libs.netty.http)

    // SPRING CLOUD DEPENDENCIES
    implementation(libs.bundles.spring.cloud.client)
    implementation(libs.gson)

    // DATABASE
    implementation(libs.spring.r2dbc)
    implementation(libs.spring.jdbc)
    implementation(libs.r2dbc)
    implementation(libs.r2dbc.postgresql)
    implementation(libs.postgresql)

    // LOMBOK
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}
