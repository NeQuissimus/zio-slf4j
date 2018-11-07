val mainScala = "2.12.7"
val allScala  = Seq("2.11.12", mainScala)

organization := "com.nequissimus"
name := "zio-slf4j"
homepage := Some(url("http://nequissimus.com/"))
licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
scalaVersion := mainScala
developers := List(
  Developer(
    "NeQuissimus",
    "Tim Steinbach",
    "steinbach.tim@gmail.com",
    url("http://nequissimus.com/")
  )
)
parallelExecution in Test := false
fork in Test := true

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "sourcecode"  % "0.1.5",
  "com.lihaoyi" %% "utest"       % "0.6.6" % Test,
  "org.scalaz"  %% "scalaz-core" % "7.2.26",
  "org.scalaz"  %% "scalaz-zio"  % "0.3.2",
  "org.slf4j"   % "slf4j-api"    % "1.7.25",
  compilerPlugin("com.github.ghik" %% "silencer-plugin" % "1.2.1"),
  "com.github.ghik" %% "silencer-lib" % "1.2.1" % Provided
)

testFrameworks += new TestFramework("utest.runner.Framework")

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-Xfuture",
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:existentials",
  "-unchecked",
  "-Yno-adapted-args",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, scalaMajor)) if scalaMajor <= 11 => Nil
  case _ =>
    Seq(
      "-opt-warnings",
      "-Ywarn-extra-implicit",
      "-opt:l:inline",
      "-opt-inline-from:<source>",
      "-Xsource:2.13"
    )
})

crossScalaVersions := allScala

addCommandAlias("format", "all scalafmtSbt scalafmt test:scalafmt")
