name := "restful-booker"

version := "0.1"

scalaVersion := "2.13.7"

// ScalaTest
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"

// Rest Assured
libraryDependencies += "io.rest-assured" % "rest-assured" % "4.4.0" % Test
libraryDependencies += "io.rest-assured" % "scala-support" % "4.4.0" % Test

// Jackson Databind
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.0"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.0"

// Type Safe
libraryDependencies += "com.typesafe" % "config" % "1.4.1"

// Log4j
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.16.0"
libraryDependencies += "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0"

// Data Faker
libraryDependencies += "io.github.etspaceman" %% "scalacheck-faker" % "7.0.0"

// Allure
libraryDependencies += "io.qameta.allure" % "allure-rest-assured" % "2.17.2"
libraryDependencies += "io.qameta.allure" % "allure-scalatest" % "2.17.2"