object Dependencies {

    object Kotlin {

        const val version = "1.8.21"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0"
    }

    object Android {

        const val core = "androidx.core:core-ktx:1.10.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
        const val activity = "androidx.activity:activity-compose:1.7.1"
        const val permissions = "com.google.accompanist:accompanist-permissions:0.31.1-alpha"
        const val appcompat = "androidx.appcompat:appcompat:1.6.1"
    }

    object Koin {

        private const val version = "3.4.0"
        private const val composeVersion = "3.4.4"

        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"

        const val compose = "io.insert-koin:koin-androidx-compose:$composeVersion"
        const val navigation = "io.insert-koin:koin-androidx-compose-navigation:$composeVersion"
        const val workManager = "io.insert-koin:koin-androidx-workmanager:$version"

        const val testAndroid = "io.insert-koin:koin-android-test:$version"
        const val test = "io.insert-koin:koin-test:$version"
        const val jUnit = "io.insert-koin:koin-test-junit4:$version"
    }

    object Compose {

        private const val version = "1.5.0-alpha03"
        const val compilerVersion = "1.4.7"
        private const val materialVersion = "1.0.1"

        const val ui = "androidx.compose.ui:ui:$version"
        const val graphics = "androidx.compose.ui:ui-graphics:$version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val material = "androidx.compose.material3:material3:$materialVersion"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val manifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val icons = "androidx.compose.material:material-icons-extended:$version"

        const val jUnit = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Test {

        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
    }

    object Room {

        private const val version = "2.5.1"

        const val ksp = "androidx.room:room-compiler:$version"
        const val roomKTX = "androidx.room:room-ktx:$version"
        const val roomRuntime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object WorkManager {

        private const val version = "2.8.1"

        const val work = "androidx.work:work-runtime-ktx:$version"
    }

    object Modules {

        const val database = ":database"
        const val app = ":app"
        const val core = ":core"
    }

    object Plugins {

        const val kspVersion = "1.8.20-1.0.11"

        const val applicationVersion = "8.2.0-alpha02"
        const val application = "com.android.application"
        const val android = "org.jetbrains.kotlin.android"
        const val library = "com.android.library"
        const val ksp = "com.google.devtools.ksp"
        const val jvm = "org.jetbrains.kotlin.jvm"
    }
}