package y2k.dash.build

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

internal class ChangelogParserTest {

    private lateinit var parser: ChangelogParser

    @BeforeEach
    fun setUp(@TempDir tempDir: File) {
        File(tempDir, "1.0.txt").createNewFile()
        File(tempDir, "1.1.txt").createNewFile()
        File(tempDir, "2.3.txt").createNewFile()
        File(tempDir, "10.11.txt").createNewFile()

        parser = ChangelogParser(tempDir)
    }

    @Test
    fun parses_versionCode() = assertEquals(1011, parser.versionCode)

    @Test
    fun parses_versionName() = assertEquals("10.11", parser.versionName)
}
