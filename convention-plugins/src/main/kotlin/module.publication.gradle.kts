import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    group = "io.github.gaaabliz.kliz"
    version = "1.0.0"
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Kliz")
            description.set("My personal shared code lib!")
            url.set("https://github.com/GaaabLiz/ktliz")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("Gabliz")
                    name.set("GaaabLiz")
                    organization.set("Gabliz INC")
                    organizationUrl.set("https://www.jetbrains.com")
                }
            }
            scm {
                url.set("https://github.com/GaaabLiz/ktliz")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        //useGpgCmd()
        println("Signing has keyName. Signing...")
        val signingKeyId: String? = System.getenv("ORG_GRADLE_PROJECT_SIGNINGKEYID")
        val signingKey: String? = System.getenv("ORG_GRADLE_PROJECT_SIGNINGKEY")
        val signingPassword: String? = System.getenv("ORG_GRADLE_PROJECT_SIGNINGPASSWORD")
        println("signingKeyId -> " + signingKeyId != null)
        println("signingKey -> " + signingKey != null)
        println("signingPassword -> " + signingPassword != null)
        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        sign(publishing.publications)
    } else {
        println("No signing.gnupg.keyName property!")
    }
}
