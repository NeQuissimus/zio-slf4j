val mainScala = "2.12.8"
val allScala  = Seq("2.11.12", mainScala, "2.13.0-M5")

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
  "org.scalaz"  %% "scalaz-core" % "7.2.27",
  "org.scalaz"  %% "scalaz-zio"  % "0.6.3",
  "org.slf4j"   % "slf4j-api"    % "1.7.26",
  compilerPlugin("com.github.ghik" %% "silencer-plugin" % "1.3.2"),
  "com.github.ghik" %% "silencer-lib" % "1.3.2" % Provided
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
  "-language:higherKinds",
  "-language:existentials",
  "-unchecked",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, 11)) =>
    Seq(
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit"
    )
  case Some((2, 12)) =>
    Seq(
      "-Xsource:2.13",
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-extra-implicit",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit",
      "-opt-inline-from:<source>",
      "-opt-warnings",
      "-opt:l:inline"
    )
  case _ => Nil
})

crossScalaVersions := allScala

addCommandAlias("format", "all scalafmtSbt scalafmt test:scalafmt")
