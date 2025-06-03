plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("java-library")
    id("net.neoforged.moddev") version "2.0.89"
    id("idea")
}



val mod_id: String by project
val minecraft_version: String by project
val minecraft_version_range: String by project
val neo_version: String by project
val neo_version_range: String by project
val loader_version_range: String by project
val mod_name: String by project
val mod_license: String by project
val mod_version: String by project
val mod_group_id: String by project
val mod_authors: String by project
val mod_description: String by project

version = mod_version
group = mod_group_id

repositories {
    mavenLocal()
    maven("https://thedarkcolour.github.io/KotlinForForge/")
}

base {
    archivesName.set(mod_id)
}

// Mojang ships Java 21 to end users starting in 1.20.5, so mods should target Java 21.
java.toolchain.languageVersion = JavaLanguageVersion.of(21)

neoForge {
    // Specify the version of NeoForge to use.
    version = neo_version

    parchment {
        val parchment_mappings_version: String by project
        val parchment_minecraft_version: String by project
        mappingsVersion.set(parchment_mappings_version)
        minecraftVersion.set(parchment_minecraft_version)
    }

    // This line is optional. Access Transformers are automatically detected
    // accessTransformers = project.files("src/main/resources/META-INF/accesstransformer.cfg")

    // Default run configurations.
    // These can be tweaked, removed, or duplicated as needed.
    runs {
        register("client") {
            client()

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        // This run config launches GameTestServer and runs all registered gametests, then exits.
        // By default, the server will crash when no gametests are provided.
        // The gametest system is also enabled by default for other run configs under the /test command.
        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
        val datagenArguments = listOf(
            "--mod", mod_id,
            "--all",
            "--output", file("src/generated/resources").absolutePath,
            "--existing", file("src/main/resources").absolutePath,
            "--existing", rootProject.file("src/main/resources").absolutePath,
        )
        if (stonecutter.eval(stonecutter.current.version, ">=1.21.4")) {
            register("clientData") {
                clientData()
                programArguments.addAll(datagenArguments)
            }
            register("serverData") {
                serverData()
                programArguments.addAll(datagenArguments)
            }
        } else {
            register("data") {
                data()
                programArguments.addAll(datagenArguments)
            }
        }

        // applies to all the run configs above
        configureEach {
            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            systemProperty("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        // define mod <-> source bindings
        // these are used to tell the game which sources are for which mod
        // multi mod projects should define one per mod
        create(mod_id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

// Sets up a dependency configuration called "localRuntime".
// This configuration should be used instead of "runtimeOnly" to declare
// a dependency that will be present for runtime testing but that is
// "optional", meaning it will not be pulled by dependents of this mod.
val localRuntime by configurations.creating
val runtimeClasspath by configurations
runtimeClasspath.extendsFrom(localRuntime)

dependencies {
    implementation("thedarkcolour:kotlinforforge-neoforge:5.8.0")
}

// This block of code expands all declared replace properties in the specified resource targets.
// A missing property will result in an error. Properties are expanded using ${} Groovy notation.
var generateModMetadata = tasks.register<ProcessResources>("generateModMetadata") {
    var replaceProperties = mapOf(
        "minecraft_version"       to minecraft_version,
        "minecraft_version_range" to minecraft_version_range,
        "neo_version"             to neo_version,
        "neo_version_range"       to neo_version_range,
        "loader_version_range"    to loader_version_range,
        "mod_id"                  to mod_id,
        "mod_name"                to mod_name,
        "mod_license"             to mod_license,
        "mod_version"             to mod_version,
        "mod_authors"             to mod_authors,
        "mod_description"         to mod_description
    )
    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from(rootProject.file("src/main/templates/"))
    into("build/generated/sources/modMetadata")
}

sourceSets.named("main") {
    resources {
        // Include universal resources
        srcDir("src/main/resources")
        // Include resources generated by data generators.
        srcDir("src/generated/resources")
        // Include the output of "generateModMetadata" as an input directory for the build
        // this works with both building through Gradle and the IDE.
        srcDir(generateModMetadata)
    }
}
// To avoid having to run "generateModMetadata" manually, make it run on every project reload
neoForge.ideSyncTask(generateModMetadata)

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8" // Use the UTF-8 charset for Java compilation
}

// IDEA no longer automatically downloads sources/javadoc jars for dependencies, so we need to explicitly enable the behavior.
idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
