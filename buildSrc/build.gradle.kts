plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    google()
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.0")
    implementation(kotlin("gradle-plugin", version = "1.3.72"))
    implementation(gradleApi())
}