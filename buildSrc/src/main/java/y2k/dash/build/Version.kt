package y2k.dash.build

class Version(versionString: String) : Comparable<Version> {
    val major: Int
    val minor: Int

    init {
        val parts = versionString.split(".").map { it.toInt() }
        if ( parts.size != 2 ) throw Exception("Invalid version number: $versionString")

        major = parts.first()
        minor = parts.last()

        if ( minor > 99 ) throw Exception("Invalid minor version: $minor")
    }

    override fun compareTo(other: Version): Int = compareValuesBy(this, other, { it.major }, { it.minor })

    fun toInt() = major * 100 + minor
}