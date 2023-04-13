plugins {
    id("java")
    kotlin("jvm") version "1.8.20"
}

group = "hide.emui.pkg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(kotlin("stdlib-jdk8"))
}
kotlin {
    jvmToolchain(11)
}