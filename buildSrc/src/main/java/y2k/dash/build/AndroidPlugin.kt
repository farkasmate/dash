package y2k.dash.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import y2k.dash.build.project.*

class AndroidPlugin : Plugin<Project> {
    private val parser = ChangelogParser("fastlane/metadata/android/en-US/changelogs")

    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureAndroid(parser.versionCode, parser.versionName)
        project.configureKapt()
        project.configureDependencies()
    }
}