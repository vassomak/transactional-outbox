description = "Atlas :: transactional-outbox"

plugins {
  kotlin("jvm")
  id("io.gitlab.arturbosch.detekt")
}

val detektVersion: String by project
val kotlinVersion: String by project
val spockVersion: String by project

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

  testImplementation("org.spockframework:spock-core:$spockVersion")

  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
}

tasks.withType<JacocoCoverageVerification> {
  violationRules {
    rule {
      limit {
        counter = "INSTRUCTION"
        minimum = "0.95".toBigDecimal()
      }
    }
    rule {
      limit {
        counter = "BRANCH"
        minimum = "0.95".toBigDecimal()
      }
    }
  }
}

tasks.check {
  dependsOn(tasks.jacocoTestCoverageVerification)
}