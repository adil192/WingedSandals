plugins {
	id("fabric-loom") version "1.10-SNAPSHOT"
	id("org.jetbrains.kotlin.jvm") version "2.1.20"
}

val modVersion: String by project
version = modVersion
val mavenGroup: String by project
group = mavenGroup

base {
	val archivesBaseName: String by project
	archivesName = archivesBaseName
}

repositories {
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.
}

fabricApi {
	configureDataGeneration {
		client = true
	}
}

dependencies {
	// To change the versions see the gradle.properties file
	val minecraftVersion: String by project
	val yarnMappings: String by project
	val loaderVersion: String by project
	val fabricApiVersion: String by project
	val fabricKotlinVersion: String by project
	minecraft("com.mojang:minecraft:${minecraftVersion}")
	mappings("net.fabricmc:yarn:${yarnMappings}:v2")
	modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricApiVersion}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${fabricKotlinVersion}")
}

tasks {
	processResources {
		inputs.property("version", project.version)

		filesMatching("fabric.mod.json") {
			expand("version" to inputs.properties["version"])
		}
	}

	jar {
		inputs.property("archivesName", project.base.archivesName)

		from(rootProject.file("LICENSE")) {
			rename { "${it}_${inputs.properties["archivesName"]}"}
		}
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release.set(21)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	compilerOptions {
		jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
	}
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}
