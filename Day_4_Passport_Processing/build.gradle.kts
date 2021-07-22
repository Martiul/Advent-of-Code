import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
}

group = "me.mrtilui"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.kotest:kotest-runner-junit5-jvm:4.1.1")
    testImplementation("io.kotest:kotest-runner-junit5:4.6.0") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core:4.6.0") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property:4.6.0") // for kotest property test
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
