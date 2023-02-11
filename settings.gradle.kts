rootProject.name = "exchanger"

include(":exchanger-bom")
include(":exchanger-core")
include(":exchanger-eureka-server")
include(":exchanger-liquibase")
include(":exchanger-service:currency-provider:service")
include(":exchanger-service:currency-provider:api")
include(":exchanger-service:dataservice:service")
include(":exchanger-service:dataservice:api")
include(":exchanger-service:exchange-rate-provider:rate-service")
include(":exchanger-service:exchange-rate-provider:rate-api")
include(":exchanger-service:transaction-manager:transaction-service")
include(":exchanger-service:transaction-manager:transaction-api")

pluginManagement {
    plugins {
        id("org.springframework.boot").version("2.7.4")
        id("io.spring.dependency-management").version( "1.0.14.RELEASE")
        kotlin("jvm").version("1.6.21")
        kotlin("plugin.spring").version( "1.6.21")
        id("org.openapi.generator").version("5.4.0")
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("spring-cloud", "2021.0.4")
            version("spring-boot", "2.7.4")
            version("springdoc", "1.6.6")

            // Spring boot
            library("spring-boot-web","org.springframework.boot","spring-boot-starter-web").versionRef("spring-boot")
            library("spring-boot-jdbc","org.springframework.boot","spring-boot-starter-data-jdbc").versionRef("spring-boot")
            library("spring-boot-jpa","org.springframework.boot","spring-boot-starter-data-jpa").versionRef("spring-boot")
            library("spring-boot-bom","org.springframework.boot","spring-boot-dependencies").versionRef("spring-boot")
            library("spring-boot-cache", "org.springframework.boot", "spring-boot-starter-cache").versionRef("spring-boot")

            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").withoutVersion()
            library("kotlin-stdlib", "org.jetbrains.kotlin", "kotlin-stdlib-jdk8").withoutVersion()
            library("kotlin-jackson", "com.fasterxml.jackson.module", "jackson-module-kotlin").withoutVersion()

            // Spring cloud
            library("spring-cloud", "org.springframework.cloud", "spring-cloud-dependencies").versionRef("spring-cloud")
            library("spring-cloud-feign", "org.springframework.cloud", "spring-cloud-starter-openfeign").withoutVersion()
            library("eureka-server", "org.springframework.cloud", "spring-cloud-starter-netflix-eureka-server").withoutVersion()
            library("eureka-client", "org.springframework.cloud", "spring-cloud-starter-netflix-eureka-client").withoutVersion()

            // Database
            library("postgres","org.postgresql","postgresql").withoutVersion()
            library("liquibase-core","org.liquibase","liquibase-core").withoutVersion()

            // Testing libraries
            library("spring-boot-test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()

            // Swagger
            library("springdoc-openapi-ui","org.springdoc","springdoc-openapi-ui").versionRef("springdoc")
        }
    }
}