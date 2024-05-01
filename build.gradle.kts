// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("io.realm.kotlin:plugin-gradle:1.15.0")

    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    kotlin("kapt") version "1.9.23"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Include settings that apply to all modules
subprojects {
    // Module-level configurations, if any
}
