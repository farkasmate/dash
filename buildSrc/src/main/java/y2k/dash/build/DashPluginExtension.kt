package y2k.dash.build

import org.gradle.api.Project
import y2k.dash.build.project.configureVersion

open class DashPluginExtension(private val project: Project) {
     var flavor : BuildFlavor = BuildFlavor.SHARED
          set(value) {
               field = value
               val parser = ChangelogParser(Settings.tagsDirectory, field)
               project.configureVersion(parser.versionCode, parser.versionName)
          }
}