pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
        mavenCentral()
        gradlePluginPortal()
    }
}

include("common")

val enabledPlatforms: String by settings
for (enabledPlatform in enabledPlatforms.split(",")) {
    include(enabledPlatform)
}

val modId: String by settings
rootProject.name = modId
