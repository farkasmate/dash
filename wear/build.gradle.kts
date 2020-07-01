import y2k.dash.build.BuildFlavor

plugins {
    id("com.android.application")
    id("y2k.dash.build")
}

android {
    defaultConfig {
        minSdkVersion(23)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dash {
    flavor = BuildFlavor.WEAR
}

dependencies {
    compileOnly("com.google.android.wearable:wearable:2.7.0")

    implementation("androidx.wear:wear:1.0.0")
    implementation("com.google.android.support:wearable:2.7.0")
    implementation(project(":shared"))
}