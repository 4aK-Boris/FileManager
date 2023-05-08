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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation(Dependencies.Koin.testAndroid)

    implementation(Dependencies.Room.roomRuntime)
    annotationProcessor(Dependencies.Room.compiler)
    ksp(Dependencies.Room.compiler)
    implementation(Dependencies.Room.roomKTX)
    implementation(Dependencies.Koin.android)
}