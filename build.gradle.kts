import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("base")
    id("idea")
    kotlin("jvm") version "1.6.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.3.1"
    id("org.springframework.boot") version "2.6.3"

    // Pins the dependency versions using the spring BOM
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

//TODO recheck
dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // support for serialization/deserialization of Kotlin classes and data classes
    // (single constructor classes can be used automatically, and those with secondary constructors or static factories
    // are also supported)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain { // Configures Java toolchain both for Kotlin and Java tasks.
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<CacheableCopyTask>("cacheableCopy", File("README.md"))
