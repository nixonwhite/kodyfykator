plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "6.0.1.5171"
}

group = "ua.org.gurt"
version = "1.1.35"
description = "kodyfykator"

val jacksonCoreVersion = "2.18.2"
val jsonVersion = "20250107"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
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

tasks.withType<Test> {
    useJUnitPlatform()
}
