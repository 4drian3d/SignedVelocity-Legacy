import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.model.PluginDependency

plugins {
    id("org.spongepowered.gradle.plugin")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(projects.signedvelocityLegacyCommon)
}

sponge {
    apiVersion("7.2.0")
    license("GPL-3")
    loader {
        name(PluginLoaders.JAVA_PLAIN)
        version("1.0")
    }
    plugin("signedvelocity") {
        displayName("SignedVelocity-Legacy")
        entrypoint("io.github._4drian3d.signedvelocity.legacy.SignedVelocitySponge")
        description(project.description)
        links {
            homepage("https://github.com/4drian3d/SignedVelocity-Legacy")
            source("https://github.com/4drian3d/SignedVelocity-Legacy")
            issues("https://github.com/4drian3d/SignedVelocity-Legacy/issues")
        }
        contributor("4drian3d") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveBaseName.set("${rootProject.name}-Legacy-Sponge")
        archiveClassifier.set("")
        doLast {
            copy {
                from(archiveFile)
                into("${rootProject.projectDir}/build")
            }
        }
    }
}