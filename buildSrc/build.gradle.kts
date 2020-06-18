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

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}