pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven("https://maven.neoforged.net/releases")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
    id("dev.kikugie.stonecutter") version "0.6"
}


stonecutter {
    create(rootProject) {
        versions("1.21.1", "1.21.3", "1.21.4", "1.21.5")
    }
}
