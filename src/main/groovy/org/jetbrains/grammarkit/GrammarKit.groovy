package org.jetbrains.grammarkit

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

class GrammarKit implements Plugin<Project> {
    @Override
    void apply(Project target) {
        def grammarKitExtension = target.extensions.create("grammarKit", GrammarKitPluginExtension.class)
        target.afterEvaluate {
            target.repositories {
                maven { url "https://cache-redirector.jetbrains.com/intellij-dependencies" }
                maven { url 'https://www.jitpack.io' }
            }
            target.dependencies.add(
                    JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME,
                    "com.github.JetBrains:Grammar-Kit:${grammarKitExtension.grammarKitRelease}",
                    {
                        exclude group: 'org.jetbrains.plugins'
                        exclude module: 'idea'
                    })
            target.dependencies.add(
                    JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME,
                    "org.jetbrains.intellij.deps.jflex:jflex:${grammarKitExtension.jflexRelease}",
                    {
                        exclude group: 'org.jetbrains.plugins'
                        exclude module: 'idea'
                        exclude module: 'ant'
                    }
            )
        }
    }
}
