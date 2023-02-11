import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("jacoco-report-aggregation")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}
group = "com.buczi.exchanger"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    jacocoAggregation(project(":exchanger-bom"))
    jacocoAggregation(project(":exchanger-eureka-server"))
    jacocoAggregation(project(":exchanger-liquibase"))
    jacocoAggregation(project(":exchanger-service:currency-provider:api"))
    jacocoAggregation(project(":exchanger-service:currency-provider:service"))
    jacocoAggregation(project(":exchanger-service:dataservice:api"))
    jacocoAggregation(project(":exchanger-service:dataservice:service"))
    jacocoAggregation(project(":exchanger-service:exchange-rate-provider:rate-api"))
    jacocoAggregation(project(":exchanger-service:exchange-rate-provider:rate-service"))
    jacocoAggregation(project(":exchanger-service:transaction-manager:transaction-api"))
    jacocoAggregation(project(":exchanger-service:transaction-manager:transaction-service"))
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
