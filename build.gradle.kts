plugins {
    java
    id("org.springframework.boot") version "3.2.11"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.sonarqube") version "5.1.0.4882"
    id("io.snyk.gradle.plugin.snykplugin") version "0.7.0"
}

group = "ua.org.gurt"
version = "1.1.27"
description = "kodyfykator"

val jacksonCoreVersion = "2.18.1"
val jsonVersion = "20240303"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonCoreVersion}")
    implementation("org.json:json:${jsonVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

springBoot {
    buildInfo()
}

snyk {
    setAutoDownload(true)
    setAutoUpdate(true)
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
