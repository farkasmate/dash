package y2k.dash.build

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import java.io.File

class AndroidPlugin : Plugin<Project> {
    private val parser = ChangelogParser("fastlane/metadata/android/en-US/changelogs")

    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureAndroid(parser.versionCode!!, parser.versionName)
        project.configureKapt()
        project.configureDependencies()
    }

    private class ChangelogParser(path: String) {
        val changelogDirectory = File(path)
        val versionCode = changelogDirectory.listFiles()?.map { it.nameWithoutExtension.toInt() }?.max()
        val versionName = File(changelogDirectory, "${versionCode}.txt").useLines { it.first() }.removePrefix("# ")
    }
}

const val lifecycleVersion = "2.2.0"
const val roomVersion      = "2.2.5"

const val material              = "com.google.android.material:material:1.2.0-alpha06"
const val espresso              = "androidx.test.espresso:espresso-core:3.2.0"
const val runner                = "androidx.test:runner:1.2.0"
const val appCompat             = "androidx.appcompat:appcompat:1.1.0"
const val constraintLayout      = "androidx.constraintlayout:constraintlayout:1.1.3"
const val fragmentKtx           = "androidx.fragment:fragment-ktx:1.2.4"
const val lifecycleLivedataKtx  = "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycleVersion}"
const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycleVersion}"
const val recyclerView          = "androidx.recyclerview:recyclerview:1.1.0"
const val roomKtx               = "androidx.room:room-ktx:${roomVersion}"
const val roomRuntime           = "androidx.room:room-runtime:${roomVersion}"
const val swipeRefreshLayout    = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01"
const val volley                = "com.android.volley:volley:1.1.1"
const val ankoCommons           = "org.jetbrains.anko:anko-commons:0.10.5"
const val roomCompiler          = "androidx.room:room-compiler:${roomVersion}"
const val junit                 = "junit:junit:4.12"

private fun Project.configureDependencies() = dependencies {
    add("androidTestImplementation", espresso)
    add("androidTestImplementation", runner)

    add("api", material)

    add("implementation", appCompat)
    add("implementation", constraintLayout)
    add("implementation", fragmentKtx)
    add("implementation", lifecycleLivedataKtx)
    add("implementation", lifecycleViewmodelKtx)
    add("implementation", recyclerView)
    add("implementation", roomKtx)
    add("implementation", roomRuntime)
    add("implementation", swipeRefreshLayout)
    add("implementation", volley)
    add("implementation", ankoCommons)

    add("kapt", roomCompiler)

    add("testImplementation", junit)
}

private fun Project.configurePlugins() {
    plugins.apply("com.android.application")
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-android-extensions")
    plugins.apply("kotlin-kapt")
}

private fun Project.configureAndroid(parsedVersionCode: Int, parsedVersionName: String) {
    this.extensions.getByType<BaseExtension>().run {
        compileSdkVersion(29) // Q
        defaultConfig {
            applicationId = "y2k.dash"
            minSdkVersion(21) // LOLLIPOP
            targetSdkVersion(29) // Q
            versionCode = parsedVersionCode
            versionName = parsedVersionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_1_8.toString()
//    }
    }
}

private fun Project.configureKapt() = this.extensions.getByType<KaptExtension>().run {
    arguments {
        arg("room.schemaLocation", "${projectDir}/schemas/")
    }
}