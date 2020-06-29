plugins {
    id("com.android.library")
    id("y2k.dash.build")
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}