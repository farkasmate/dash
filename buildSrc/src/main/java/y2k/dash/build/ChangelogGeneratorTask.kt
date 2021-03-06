package y2k.dash.build

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class ChangelogGeneratorTask : DefaultTask() {
    @TaskAction
    fun generate() {
        BuildFlavor.values().forEach { flavor ->
            val parser = ChangelogParser(Settings.tagsDirectory, flavor)
            val appChangelogFile = File(Settings.changelogDirectory, "${parser.versionCode}.txt")
            parser.changelogFile.copyTo(appChangelogFile, overwrite = true)
        }
    }
}