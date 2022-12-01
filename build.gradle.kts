plugins {
    java
}

group = "me.gabytm"
version = "2022"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
}