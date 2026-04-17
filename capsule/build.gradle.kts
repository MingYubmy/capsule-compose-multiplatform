@file:OptIn(ExperimentalWasmDsl::class)

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "dev.mingyubmy"
version = "0.0.1"

kotlin {
    jvm()
    androidLibrary {
        namespace = "dev.mingyubmy"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withJava() // enable java compilation support

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(
                    JvmTarget.JVM_21
                )
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    // linuxX64()
    wasmJs { browser() }
    js(IR) { browser() }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiTooling)
        }

        jvmMain.dependencies {
            implementation(libs.compose.uiTooling)
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
        }
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "capsule", version.toString())

    pom {
        name = "capsule-compose-multiplatform"
        description = "A Capsule for Compose Multiplatform Library."
        inceptionYear = "2026"
        url = "https://github.com/MingYubmy/capsule-compose-multiplatform"
        licenses {
            license {
                name = "Apache License 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "mingyubmy"
                name = "MingYubmy"
                url = "https://github.com/MingYubmy"
            }
        }
        scm {
            url = "https://github.com/MingYubmy/capsule-compose-multiplatform"
            connection = "scm:git:git://github.com/MingYubmy/capsule-compose-multiplatform.git"
            developerConnection = "scm:git:ssh://git@github.com/MingYubmy/capsule-compose-multiplatform.git"
        }
    }
}
