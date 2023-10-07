plugins {
    id("java-platform")
    id("alliance.base-conventions")
}

indra {
    configurePublications {
        from(components["javaPlatform"])
    }
}

dependencies {
    constraints {
        sequenceOf(
                "core"
        ).forEach {
            api(project(":alliance-$it"))
        }
    }
}