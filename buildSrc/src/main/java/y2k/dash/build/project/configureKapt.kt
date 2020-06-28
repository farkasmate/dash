package y2k.dash.build.project

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun Project.configureKapt() = this.extensions.getByType<KaptExtension>().run {
    arguments {
        arg("room.schemaLocation", "${projectDir}/schemas/")
    }
}