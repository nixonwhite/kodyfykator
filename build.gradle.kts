plugins {
    java
    id("org.springframework.boot") version "3.2.6"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.sonarqube") version "5.0.0.4638"
    id("io.snyk.gradle.plugin.snykplugin") version "0.6.1"
}

group = "ua.org.gurt"
version = "1.1.21"
description = "kodyfykator"

val jacksonCoreVersion = "2.17.1"
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
