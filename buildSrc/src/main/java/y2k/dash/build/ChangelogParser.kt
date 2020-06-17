package y2k.dash.build

import java.io.File

class ChangelogParser(path: String) {
    private val changelogDirectory = File(path)

    val versionCode = changelogDirectory.listFiles()?.map { it.nameWithoutExtension.toInt() }?.max()
    val versionName = File(changelogDirectory, "${versionCode}.txt").useLines { it.first() }.removePrefix("# ")
}