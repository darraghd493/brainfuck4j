plugins {
    id("java")
    id("com.gradleup.shadow") version "9.6.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.ow2.asm:asm-bom:9.10.1"))
    implementation("org.ow2.asm:asm")
    implementation("org.ow2.asm:asm-tree")
    implementation("info.picocli:picocli:4.7.7")

    implementation(project(":ast"))
    implementation(project(":parser"))
    implementation(project(":interpreter"))
    implementation(project(":transpiler"));
}

tasks.shadowJar {
    archiveAppendix = "standalone"
    manifest {
        attributes(
            "Main-Class" to "me.darragh.brainfuck4j.cli.Main"
        )
    }
}
