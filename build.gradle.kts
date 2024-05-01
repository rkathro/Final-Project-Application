// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    kotlin("kapt") version "1.9.23"
    //id("com.google.gms.google-services") version "4.4.1" apply false
}

buildscript {


    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("com.google.gms:google-services:4.3.10") // Google Services plugin
    }
}