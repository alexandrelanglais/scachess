name := "ScaChess"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "ch.qos.logback"             % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging"  % "3.7.2"
libraryDependencies += "org.scalactic"              %% "scalactic"      % "3.0.4"
libraryDependencies += "org.scalatest"              %% "scalatest"      % "3.0.4" % "test"
libraryDependencies += "com.github.pathikrit"       %% "better-files"   % "3.2.0"
libraryDependencies += "com.beachape"               %% "enumeratum"     % "1.5.12"

wartremoverErrors ++= Warts.allBut(
  Wart.DefaultArguments,
  Wart.Nothing,
  Wart.Equals,
  Wart.NonUnitStatements
)
