package y2k.dash.build.project

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroid(parsedVersionCode: Int, parsedVersionName: String) {
    this.extensions.getByType<BaseExtension>().run {
        compileSdkVersion(29) // Q
        defaultConfig {
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