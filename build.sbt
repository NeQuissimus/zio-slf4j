val mainScala = "2.12.10"
val allScala  = Seq(mainScala, "2.13.1")

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
pgpPublicRing := file("/tmp/public.asc")
pgpSecretRing := file("/tmp/secret.asc")
releaseEarlyWith := SonatypePublisher
scmInfo := Some(
  ScmInfo(url("https://github.com/NeQuissimus/zio-slf4j/"), "scm:git:git@github.com:NeQuissimus/zio-slf4j.git")
)

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "sourcecode"  % "0.2.1",
  "com.lihaoyi" %% "utest"       % "0.7.4" % Test,
  "org.scalaz"  %% "scalaz-core" % "7.2.30" % Optional,
  "dev.zio"     %% "zio"         % "1.0.0-RC17",
  "org.slf4j"   % "slf4j-api"    % "1.7.30",
  compilerPlugin("com.github.ghik" % "silencer-plugin" % "1.6.0" cross CrossVersion.full),
  "com.github.ghik" % "silencer-lib" % "1.6.0" % Provided cross CrossVersion.full
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
