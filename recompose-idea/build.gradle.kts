import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.intellij") version "1.10.1"
    id("org.jetbrains.changelog") version "0.5.0"
}

project.group = "xyz.pocmo.recompose"
project.version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":recompose-ast", "default"))
    implementation(project(":recompose-parser", "default"))
    implementation(project(":recompose-composer", "default"))
}

intellij {
    pluginName = "Recompose"
    version = "2021.3"
    updateSinceUntilBuild = false
    downloadSources = true
    updateSinceUntilBuild = true

    setPlugins("IntelliLang", "Kotlin")
}

tasks {
    // Set the compatibility versions to 1.8
    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
    listOf("compileKotlin", "compileTestKotlin").forEach {
        getByName<KotlinCompile>(it) {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    patchPluginXml {
        sinceBuild("213")
        untilBuild("223.*")
    }
}
