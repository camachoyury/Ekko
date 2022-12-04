plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
   id ("kotlinx-serialization")


}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")

                // Ktor
                implementation("io.ktor:ktor-client-core:1.6.0")
                implementation("io.ktor:ktor-client-json:1.6.0")
                implementation("io.ktor:ktor-client-logging:1.6.0")
                implementation("io.ktor:ktor-client-serialization:1.6.0")

                // Kotlinx Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")


            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:1.6.0")

            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.0")

            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.camachoyury.core"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}