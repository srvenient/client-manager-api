pluginManagement {
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://repo.revengenetwork.es/repository/libs/")
        mavenLocal()
    }
}

rootProject.name = "alliance-backend"

sequenceOf("core", "bom").forEach {
    include("alliance-$it")
    project(":alliance-$it").projectDir = file(it)
}
