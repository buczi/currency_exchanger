plugins {
    `java-platform`
}

javaPlatform.allowDependencies()

dependencies {
    // Exchanger
    api(project(":exchanger-core"))
    // Spring boot
    api(libs.spring.boot.jdbc)
    api(libs.spring.boot.jpa)
    api(libs.spring.boot.web)

    // Kotlin
    api(libs.kotlin.jackson)
    api(libs.kotlin.reflect)
    api(libs.kotlin.stdlib)

    // Spring cloud
    api(platform(libs.spring.cloud))
    api(libs.spring.cloud.feign)
    api(libs.eureka.client)

    // Postgres
    api(libs.postgres)

    // Swagger
    api(libs.springdoc.openapi.ui)

    // Testing
    api(libs.spring.boot.test)
}

description = "exchanger-bom"