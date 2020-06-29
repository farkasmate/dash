package y2k.dash.build.project

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

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
const val roomCompiler          = "androidx.room:room-compiler:${roomVersion}"
const val junit                 = "junit:junit:4.12"

internal fun Project.configureDependencies() = dependencies {
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

    add("kapt", roomCompiler)

    add("testImplementation", junit)
}