plugins {
    java
}

allprojects {
    apply<JavaPlugin>()
    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name()
        }
    }
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}