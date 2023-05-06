plugins {
    id(Dependencies.Plugins.application) version Dependencies.Plugins.applicationVersion apply false
    id(Dependencies.Plugins.android) version Dependencies.Kotlin.version apply false
    id(Dependencies.Plugins.jvm) version Dependencies.Kotlin.version apply false
    id(Dependencies.Plugins.library) version Dependencies.Plugins.applicationVersion apply false
}