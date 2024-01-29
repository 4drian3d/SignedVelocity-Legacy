plugins {
    alias(libs.plugins.runpaper)
    alias(libs.plugins.pluginyml.bukkit)
    alias(libs.plugins.shadow)
}

dependencies {
    compileOnly(libs.paper.api)
    implementation(projects.signedvelocityLegacyCommon)
}

tasks {
    clean {
        // Deletes the directory that is generated by the runPaper plugin
        delete("run")
    }
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveBaseName.set("${rootProject.name}-Legacy-Bukkit")
        archiveClassifier.set("")
        doLast {
            copy {
                from(archiveFile)
                into("${rootProject.projectDir}/build")
            }
        }
    }
    runServer {
        minecraftVersion("1.16.5")
    }
}

bukkit {
    name = "SignedVelocity-Legacy"
    main = "io.github._4drian3d.signedvelocity.legacy.bukkit.SignedVelocity"
    authors = listOf("4drian3d")

    foliaSupported = false
    apiVersion = "1.13"
}