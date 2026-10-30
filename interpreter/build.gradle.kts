plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ast"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.1.3")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation(project(":parser"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.1.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.1.3")
}

tasks.test {
    useJUnitPlatform()
}
