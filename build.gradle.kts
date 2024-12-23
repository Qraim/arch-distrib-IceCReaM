plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.thrift:libthrift:0.21.0")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    testImplementation("org.mockito:mockito-core:2.1.0")
}

tasks.test {
    useJUnitPlatform()
}

val thriftSourceDir = file("src/main/ICRM.thrift")
val thriftOutputDir = file("src/main/java")

tasks.register<Exec>("generateThrift") {
    group = "build"
    inputs.file(thriftSourceDir)
    outputs.dir(thriftOutputDir)

    commandLine("thrift", "--gen", "java", "-out", thriftOutputDir.absolutePath, thriftSourceDir.absolutePath)
}

tasks.compileJava {
    dependsOn("generateThrift")
}

application {
    mainClass.set("org.example.CRMServer")
}