import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("base")
    id("idea")
    kotlin("jvm") version "1.3.50"
}

//TODO recheck
dependencies {
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.0")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")
    implementation(kotlin("stdlib:1.3.50"))
    testImplementation("junit:junit:4.12")
}

repositories {
    gradlePluginPortal()
    jcenter()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}