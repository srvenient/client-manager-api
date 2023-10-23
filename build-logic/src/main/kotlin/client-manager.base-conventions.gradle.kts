import com.diffplug.gradle.spotless.FormatExtension
import java.util.*

plugins {
  id("net.kyori.indra")
  id("com.diffplug.spotless")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
  javaVersions {
    target(17)
    minimumToolchain(17)
    strictVersions(true)
  }
}

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  compileOnly(libs.annotations)
}

spotless {
  fun FormatExtension.applyCommon() {
    trimTrailingWhitespace()
    endWithNewline()
    indentWithSpaces(2)
  }
  java {
    importOrderFile(rootProject.file(".spotless/client-manager.importorder"))
    removeUnusedImports()
    applyCommon()
  }
  kotlinGradle {
    applyCommon()
  }
}

tasks {
  jar {
    manifest {
      attributes(
        "Specification-Version" to project.version,
        "Specification-Vendor" to "emptyte-team",
        "Implementation-Build-Date" to Date()
      )
    }
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name()
    dependsOn(spotlessApply)
    options.compilerArgs.add("-parameters")
  }
}
