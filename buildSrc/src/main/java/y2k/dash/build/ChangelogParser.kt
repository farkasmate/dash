package y2k.dash.build

import java.io.File
import java.lang.Exception

class ChangelogParser(path: String) {
    private val changelogDirectory = File(path)
    private val version: Version

    init {
        val files = changelogDirectory.listFiles()
        if ( files.isNullOrEmpty() ) throw Exception("No changelogs found in: $changelogDirectory")

        val versions = files.map { Version(it.nameWithoutExtension) }
        version = versions.max()!!
    }

    val versionCode = version.toInt()
    val versionName = "${version.major}.${version.minor}"
}