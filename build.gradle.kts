plugins {
    application
    kotlin("jvm") version "1.6.20"
}

group = "de.herrlau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testApi("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}