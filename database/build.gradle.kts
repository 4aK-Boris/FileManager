plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.android)
    id(Dependencies.Plugins.ksp)
}

android {
    namespace = "dmitriy.losev.database"
    compileSdk = 33

    defaultConfig {

        minSdk = 24

        testInstrumentationRunner = "dmitriy.losev.database.core.InstrumentationTestRoomRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "META-INF/atomicfu.kotlin_module"
        }
    }
}

dependencies {

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appcompat)

    annotationProcessor(Dependencies.Room.compiler)

    ksp(Dependencies.Room.compiler)

    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.roomKTX)
    implementation(Dependencies.Koin.android)

    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Koin.testAndroid)
}