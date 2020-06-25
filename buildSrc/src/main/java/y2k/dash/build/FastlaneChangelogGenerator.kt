package y2k.dash.build

import java.io.File

object FastlaneChangelogGenerator {
    private val parser = ChangelogParser(Settings.changelogDirectory)

    @JvmStatic
    fun main(args: Array<String>) {
        parser.changelogFile.copyTo(File(Settings.changelogDirectory, "${parser.versionCode}.txt"))
    }
}
