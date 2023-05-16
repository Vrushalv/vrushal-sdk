import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	id("maven-publish")
}

group = "com.liblab"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.squareup.okhttp3:okhttp:4.11.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-inline:2.13.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

publishing {
	repositories {
		maven {
			url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
		}
	}
	publications {
		create<MavenPublication>("relocation") {
			pom {
				// Old artifact coordinates
				groupId = "com.liblab.sdk"
				artifactId = "lib"
				version = "2.0.0"

				distributionManagement {
					relocation {
						// New artifact coordinates
						groupId.set("com.liblab-sdk")
						artifactId.set("lib")
						version.set("2.0.0")
						message.set("groupId has been changed")
					}
				}
			}
		}

	}
}