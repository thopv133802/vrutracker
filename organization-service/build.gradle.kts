import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*
plugins {
    kotlin("plugin.jpa") version "1.2.71"
    id("org.springframework.boot") version "2.1.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
    id("idea")
    id("com.google.protobuf") version "0.8.10"
}

group = "com.thopham.projects.research"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "Greenwich.SR2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("org.springframework.cloud:spring-cloud-starter-config")
    //Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
//    testImplementation("org.springframework.boot:
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")spring-boot-starter-test")

    //json web token
    implementation("io.jsonwebtoken:jjwt:0.9.0")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.security:spring-security-test")

    //Gson
    implementation ("com.google.code.gson:gson:2.8.5")
    //Grpc
    implementation("io.grpc:grpc-netty-shaded:1.22.1")
    implementation("io.grpc:grpc-protobuf:1.22.1")
    implementation("io.grpc:grpc-stub:1.22.1")
    implementation("com.google.protobuf:protobuf-java:3.9.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.9.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.22.1"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}
sourceSets {
    main {
        proto {
            srcDir("/home/thopham/IdeaProjects/vrutracker/protos")
        }
    }
}