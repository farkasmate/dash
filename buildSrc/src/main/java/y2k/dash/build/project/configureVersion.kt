package y2k.dash.build.project

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureVersion(code: Int, name: String) {
    this.extensions.getByType<BaseExtension>().run {
        defaultConfig {
            versionCode = code
            versionName = name
        }
    }
}