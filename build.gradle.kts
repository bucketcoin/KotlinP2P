import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
}

group = "dev.crash"
version = "0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

apply(plugin = "maven-publish")

dependencies {
    implementation("io.ktor:ktor-server-core:1.6.3")
    implementation("io.ktor:ktor-server-netty:1.6.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.30")
}

publishing {
    publications {
        create<MavenPublication>("maven"){
            groupId = "dev.crash"
            artifactId = "kotlinp2p"
            version = "0.1"
        }
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.withType<PublishToMavenLocal> {
    doLast {
        project.file("/build/libs/${project.name}-$version-all.jar").copyTo(file("C:\\Users\\${System.getProperties()["user.name"]}\\.m2\\repository\\dev\\crash\\kotlinp2p\\0.1\\${project.name}-$version-all.jar"), overwrite = true)
    }
}