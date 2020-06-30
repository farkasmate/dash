package y2k.dash.build

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class ChangelogParserTest {
    @TempDir
    lateinit var tempDir: File

    @BeforeEach
    fun setUp() {
        File(tempDir, "1.0.txt").createNewFile()
        File(tempDir, "1.1.txt").createNewFile()
        File(tempDir, "2.3.txt").createNewFile()
        File(tempDir, "10.11.txt").createNewFile()
    }

    @Test
    fun sharedVersion() {
        val parser = ChangelogParser(tempDir, BuildFlavor.SHARED)
        assertEquals(10110, parser.versionCode)
        assertEquals("10.11", parser.versionName)
    }

    @Test
    fun appVersion() {
        val parser = ChangelogParser(tempDir, BuildFlavor.APP)
        assertEquals(10111, parser.versionCode)
        assertEquals("10.11", parser.versionName)
    }

    @Test
    fun wearVersion() {
        val parser = ChangelogParser(tempDir, BuildFlavor.WEAR)
        assertEquals(10112, parser.versionCode)
        assertEquals("10.11", parser.versionName)
    }
}
