val kotlin_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0"
    id("com.vanniktech.maven.publish") version "0.36.0"
}

group = "fun.j2k"
version = "0.2.1"

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "maxbotkt", version.toString())

    pom {
        name = "maxbotkt"
        description = "Small MAX bot api declarations library"
        inceptionYear = "2026"

        url = "https://github.com/jaka2005/maxbotkt/"

        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/licenses/MIT"
            }
        }
        developers {
            developer {
                id = "jaka2005"
                name = "jaka2005"
                url = "https://github.com/jaka2005/"
            }
        }
        scm {
            url = "https://github.com/jaka2005/maxbotkt/"
            connection = "scm:git:git://github.com/jaka2005/maxbotkt.git"
            developerConnection = "scm:git:ssh://git@github.com/jaka2005/maxbotkt.git"
        }
    }
}

