pluginManagement {
    includeBuild("build-logic")
}

rootProject.name = "client-manager"

sequenceOf("core").forEach {
    include("client-manager-$it")
    project(":client-manager-$it").projectDir = file(it)
}
