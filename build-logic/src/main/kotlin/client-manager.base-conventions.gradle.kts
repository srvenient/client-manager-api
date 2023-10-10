import gradle.kotlin.dsl.accessors._adcf4d67bb571b32ed21abd8736e6c1e.indra

plugins {
  id("net.kyori.indra.publishing")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

indra {
  javaVersions {
    target(17)
    minimumToolchain(17)
    strictVersions(true)
  }

  github("srvenient", "alliance") {
    ci(true)
  }
  mitLicense()

  signWithKeyFromPrefixedProperties("alliance")
  configurePublications {
    pom {
      developers {
        developer {
          id.set("srvenient")
          name.set("Nelson Roa")
          url.set("https://github.com/srvenient")
          email.set("srvenient@gmail.com")
        }
      }
    }
  }
}
