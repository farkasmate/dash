plugins {
    id("com.android.application")
    id("y2k.dash.build")
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":shared"))
}