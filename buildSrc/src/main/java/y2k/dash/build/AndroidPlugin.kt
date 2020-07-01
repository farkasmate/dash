package y2k.dash.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import y2k.dash.build.project.*

class AndroidPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create(
                Settings.extensionName,
                DashPluginExtension::class.java,
                project
        )
        val parser = ChangelogParser(Settings.tagsDirectory, extension.flavor)

        project.configurePlugins()
        project.configureAndroid()
        project.configureVersion(parser.versionCode, parser.versionName)
        project.configureKapt()
        project.configureDependencies()
    }
}