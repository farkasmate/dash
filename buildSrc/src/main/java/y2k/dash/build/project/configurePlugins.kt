package y2k.dash.build.project

import org.gradle.api.Project

internal fun Project.configurePlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-android-extensions")
    plugins.apply("kotlin-kapt")
}