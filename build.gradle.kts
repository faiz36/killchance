plugins {
    kotlin("jvm") version "1.9.20"
    id("xyz.jpenilla.run-paper") version "2.2.0"
}

group = "io.github.faiz36"
version = "0.0.1"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    runServer {
        minecraftVersion("1.20.1")
    }
}

dependencies {
    implementation("io.github.monun:kommand-api:3.1.7")
    implementation("io.github.monun:invfx-api:3.3.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
}