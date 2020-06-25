import org.jetbrains.kotlin.config.KotlinCompilerVersion

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

val fatJar by configurations.creating

dependencies {
    fatJar(kotlin("stdlib", version = KotlinCompilerVersion.VERSION))

    implementation("com.android.tools.build:gradle:4.0.0")
    implementation(kotlin("gradle-plugin", version = KotlinCompilerVersion.VERSION))
    implementation(gradleApi())

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.jar {
    archiveBaseName.set("fastlaneChangelogGenerator")

    manifest {
        attributes["Main-Class"] = "y2k.dash.build.FastlaneChangelogGenerator"
    }

    from(fatJar.files.map({ if (it.isDirectory) it else zipTree(it) }))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
