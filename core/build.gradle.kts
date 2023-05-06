plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.android)
}

android {
    namespace = "dmitriy.losev.core"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }
}

dependencies {

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.lifecycle)
    implementation(Dependencies.Android.activity)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.graphics)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.material)

    implementation(Dependencies.Koin.compose)

    testImplementation(Dependencies.Test.jUnit)

    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Compose.jUnit)
    androidTestImplementation(Dependencies.Koin.test)

    debugImplementation(Dependencies.Compose.tooling)
    debugImplementation(Dependencies.Compose.manifest)
}