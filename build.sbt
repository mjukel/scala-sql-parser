import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val root = project.in(file("."))
  .aggregate(sqlParserJVM, sqlParserJS)
  .settings(publish := {})

lazy val sqlParser = crossProject(JVMPlatform, JSPlatform)
    .crossType(CrossType.Pure)
	.in(file("."))	
	.settings(
		name := "scala-sql-parser",
		organization := "de.keephealth",
		version := "1.0.0-KH",
		scalacOptions ++= Seq("-deprecation", "-unchecked"),
		libraryDependencies ++= Seq(
		  "org.scala-lang.modules" %%% "scala-parser-combinators" % "1.1.2-KH"
		)		
	).jvmSettings(
		scalaVersion := "2.11.12"
	).jsSettings(
		scalaVersion := "2.12.6"
	)

lazy val sqlParserJVM = sqlParser.jvm
lazy val sqlParserJS = sqlParser.js