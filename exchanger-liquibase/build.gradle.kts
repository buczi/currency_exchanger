plugins{
    `java-library`
}

dependencies {
    implementation(platform(project(":exchanger-bom")))

    api(libs.liquibase.core)
}

description = "exchanger-liquibase"