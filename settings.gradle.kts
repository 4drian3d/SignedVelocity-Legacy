@file:Suppress("UnstableApiUsage")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "SignedVelocity-Legacy"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

plugins {
    id("org.spongepowered.gradle.plugin") version "2.1.1"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

arrayOf(
    "common",
    "bukkit",
    "sponge"
).forEach {
    include("signedvelocity-legacy-$it")
    project(":signedvelocity-legacy-$it").projectDir = file(it)
}