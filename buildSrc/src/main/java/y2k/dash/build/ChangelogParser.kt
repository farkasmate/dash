package y2k.dash.build

import java.io.File
import java.lang.Exception

class ChangelogParser(changelogDirectory: File, flavor: BuildFlavor = BuildFlavor.SHARED) {
    private val version: Version

    val changelogFile: File

    init {
        val files = changelogDirectory.listFiles()
        if ( files.isNullOrEmpty() ) throw Exception("No changelogs found in: $changelogDirectory")

        val versions = files.map { Version(it.nameWithoutExtension) }
        version = versions.max()!!

        changelogFile = files.first { file -> file.name == "${version.major}.${version.minor}.txt" }
    }

    val versionCode = version.toInt() * 10 + flavor.id
    val versionName = "${version.major}.${version.minor}"
}