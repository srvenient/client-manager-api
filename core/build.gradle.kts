plugins {
  id("alliance.common-conventions")
  id("org.springframework.boot") version "3.1.4"
  id("io.spring.dependency-management") version "1.1.3"
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  runtimeOnly("mysql:mysql-connector-java:8.0.28")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
