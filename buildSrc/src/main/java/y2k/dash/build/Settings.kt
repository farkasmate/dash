package y2k.dash.build

import java.io.File

object Settings {
  val changelogDirectory = File("fastlane/metadata/android/en-US/changelogs")
  val tagsDirectory      = File(changelogDirectory, "tags")
}
