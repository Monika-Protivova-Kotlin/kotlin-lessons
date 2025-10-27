plugins {
    kotlin("jvm") version "2.1.20"
    application
}

group = "com.motycka.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

application {
    mainClass.set("com.motycka.edu.MainKt")
}

// Custom tasks for individual courses
tasks.register<JavaExec>("generateProgrammingCourse") {
    group = "course generation"
    description = "Generate Programming in Kotlin course HTML files"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.motycka.edu.ProgrammingInKotlinMainKt")
}

tasks.register<JavaExec>("generateBackendCourse") {
    group = "course generation"
    description = "Generate Backend Development in Kotlin course HTML files"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.motycka.edu.BackendDevelopmentInKotlinMainKt")
}

tasks.register<JavaExec>("generateAllCourses") {
    group = "course generation"
    description = "Generate all course HTML files"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("com.motycka.edu.MainKt")
}
