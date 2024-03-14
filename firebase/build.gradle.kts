@file:Suppress("UseTomlInstead")
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.google.gms.google-services").version("4.4.0")
    id("module.publication")
}

//apply(plugin = "com.google.gms.google-services:4.4.0")

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    targetHierarchy.default()
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.play.services.auth)
                implementation(platform(libs.firebase.bom.get()))
                implementation("com.google.firebase:firebase-auth-ktx")
                implementation("com.google.firebase:firebase-firestore-ktx")
                implementation("com.google.firebase:firebase-storage-ktx")
                implementation("com.google.firebase:firebase-database-ktx")
                implementation("com.google.firebase:firebase-messaging-ktx")
            }
        }
        val jvmMain by getting {
            dependencies {

            }
        }
    }
}

android {
    namespace = "io.github.gaaabliz.kliz.firebase.android"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}