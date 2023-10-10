import com.diffplug.gradle.spotless.FormatExtension
import java.util.*

plugins {
  id("client-manager.base-conventions")
  id("net.kyori.indra")
  id("net.kyori.indra.crossdoc")
  id("net.kyori.indra.checkstyle")
  id("net.kyori.indra.licenser.spotless")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

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
    applyCommon()
  }
  kotlinGradle {
    applyCommon()
  }
}

indraCrossdoc {
  baseUrl().set(providers.gradleProperty("javadocPublishRoot"))
  nameBasedDocumentationUrlProvider {
    projectNamePrefix.set("client-manager-")
  }
}

java {
  withJavadocJar()
}

tasks {
  generateOfflineLinks {
  }

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
    dependsOn("spotlessApply")
    options.compilerArgs.add("-parameters")
  }
}
