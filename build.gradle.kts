plugins {
    java
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "6.3.1.5724"
}

group = "ua.org.gurt"
version = "1.1.47"
description = "kodyfykator"

val jacksonCoreVersion = "2.20.0"
val jsonVersion = "20250517"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
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
