// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.kover)
    alias(libs.plugins.spotless)
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.android") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }
}

detekt {
    toolVersion = libs.versions.detekt.get()
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
        verify {
            /*
            // TODO enable the minimum code coverage threshold
            rule {
                bound {
                    minValue = 80
                    coverageUnits = CoverageUnit.BRANCH
                }

                bound {
                    minValue = 80
                    coverageUnits = CoverageUnit.LINE
                }
            }
             */
        }
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
                    "*Companion*",
                )
                packages(
                    "*.di",
                    "*.generated",
                )
            }
        }
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint(libs.versions.ktlint.get())
            .editorConfigOverride(
                mapOf(
                    "ktlint_function_naming_ignore_when_annotated_with" to
                        "Composable,androidx.compose.runtime.Composable",
                ),
            )
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlinGradle {
        target("**/*.kts")
        ktlint(libs.versions.ktlint.get())
        trimTrailingWhitespace()
        endWithNewline()
    }
}
// used to enable dependency locking for all resolvable dependency configurations within a project.
// This ensures that every build uses the exact same version of each dependency,
// leading to reproducible and consistent builds across different machines and environments.
dependencyLocking {
    lockAllConfigurations()
}
