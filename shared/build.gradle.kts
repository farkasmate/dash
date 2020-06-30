import y2k.dash.build.BuildFlavor

plugins {
    id("com.android.library")
    id("y2k.dash.build")
}

dash {
    flavor = BuildFlavor.SHARED
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}