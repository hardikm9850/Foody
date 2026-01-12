// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.kover)
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.android") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }
}

detekt {
    toolVersion = libs.versions.detekt.toString()
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    parallel = true
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(false)
        sarif.required.set(true)
    }
}
kover {
    useJacoco()

    reports {
        total {
            html {
                onCheck.set(true)
            }
            xml {
                onCheck.set(true)
            }
        }
        filters {
            excludes {
                classes(
                    "*BuildConfig",
                    "*Hilt*",
                    "*_Factory*",
                    "*_MembersInjector*",
                    "*Companion*"
                )
                packages(
                    "*.di",
                    "*.generated"
                )
            }
        }
    }
}
