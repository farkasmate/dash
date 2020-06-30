import y2k.dash.build.BuildFlavor

plugins {
    id("com.android.application")
    id("y2k.dash.build")
}

android {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dash {
    flavor = BuildFlavor.APP
}

dependencies {
    implementation(project(":shared"))
}