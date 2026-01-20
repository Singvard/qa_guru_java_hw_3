plugins {
    java
}

group = "guru.qa"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val datafakerVersion = "2.5.3"
    val slf4jVersion = "2.0.17"
    val logbackVersion = "1.5.24"
    val selenideVersion = "7.13.0"
    val junitVersion = "6.0.2"
    val assertjVersion = "3.27.6"

    implementation("net.datafaker:datafaker:$datafakerVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("com.codeborne:selenide:$selenideVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec>().configureEach {
    systemProperty("file.encoding", "UTF-8")
    systemProperty("sun.jnu.encoding", "UTF-8")
    systemProperty("sun.stdout.encoding", "UTF-8")
}

tasks.test {
    useJUnitPlatform()
}