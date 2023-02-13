import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.platform.TargetPlatformVersion.NoVersion.description

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

group = "com.buczi.exchanger"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(project(":exchanger-bom")))
    implementation(project(":exchanger-service:currency-provider:api"))
    implementation(project(":exchanger-service:transaction-manager:transaction-api"))

    implementation(libs.spring.boot.cache)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

description = "exchanger-currency-provider-service"