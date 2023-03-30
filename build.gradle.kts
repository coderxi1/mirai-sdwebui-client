plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.14.0"
}

group = "com.coderxi"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.26")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.alibaba.fastjson2:fastjson2:2.0.26")
}