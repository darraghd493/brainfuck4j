plugins {
    id("java")
}

group = "me.darragh"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.ow2.asm:asm-bom:9.10.1"))
    implementation("org.ow2.asm:asm")
    implementation("org.ow2.asm:asm-tree")

    implementation(project(":ast"))

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(project(":parser"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}