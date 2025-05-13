plugins {
	id("org.jetbrains.kotlin.jvm") version "2.1.20"
	id("fabric-loom") version "1.10-SNAPSHOT"
}

val javaVersion = if (stonecutter.eval(stonecutter.current.version, ">=1.20.5"))
	JavaVersion.VERSION_21 else JavaVersion.VERSION_17
val mcVersionRangeForFabric = when (stonecutter.current.version) {
	"1.20.1" -> ">=1.20 <1.20.5"
	"1.20.5" -> ">=1.20.5 <1.21"
	"1.21.1" -> ">=1.21 <1.21.2"
	"1.21.2" -> ">=1.21.2 <1.21.4"
	"1.21.4" -> ">=1.21.4 <1.21.5"
	"1.21.5" -> ">=1.21.5 <1.22"
	else -> "~${stonecutter.current.version}"
}
val mcVersionRangeForFileName = when (stonecutter.current.version) {
	"1.20.1" -> "1.20-1.20.4"
	"1.20.5" -> "1.20.5-1.20.6"
	"1.21.1" -> "1.21-1.21.1"
	"1.21.2" -> "1.21.2-1.21.3"
	"1.21.4" -> "1.21.4"
	"1.21.5" -> "1.21.5"
	else -> stonecutter.current.version
}

version = "${property("mod.version")}+${mcVersionRangeForFileName}"
group = property("mod.group")!!

base {
	archivesName = "${property("mod.id")}-fabric"
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
	minecraft("com.mojang:minecraft:${stonecutter.current.project}")
	mappings("net.fabricmc:yarn:${property("deps.yarn_mappings")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${property("deps.fabric_kotlin")}")
}

tasks {
	processResources {
		inputs.property("minecraft", stonecutter.current.version)
		inputs.property("java", javaVersion.majorVersion)
		inputs.property("minecraftVersionRange", mcVersionRangeForFabric)
		inputs.property("version", project.version)

		filesMatching("fabric.mod.json") {
			expand(mapOf(
				"minecraft" to inputs.properties["minecraft"],
				"java" to inputs.properties["java"],
				"minecraftVersionRange" to inputs.properties["minecraftVersionRange"],
				"version" to inputs.properties["version"],
			))
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
	options.release.set(javaVersion.majorVersion.toInt())
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
	compilerOptions {
		jvmTarget = when (javaVersion) {
			JavaVersion.VERSION_17 -> org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
			JavaVersion.VERSION_21 -> org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
			else -> throw IllegalStateException("Unsupported Java version: $javaVersion")
		}
	}
}

java {
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this line, sources will not be generated.
	// withSourcesJar()

	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
}

loom {
	runConfigs.all {
		ideConfigGenerated(true) // Run configurations are not created for subprojects by default
		runDir = "../../run" // Use a shared run folder and create separate worlds
	}
}
