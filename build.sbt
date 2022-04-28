import BuildHelper._
import MimaSettings.mimaSettings
import explicitdeps.ExplicitDepsPlugin.autoImport.moduleFilterRemoveValue
import sbt.Keys

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    organization := "org.ergoplatform",
    homepage     := Some(url("https://ergoplatform.org")),
    licenses := List(
      "MIT" -> url("https://opensource.org/licenses/MIT")
    ),
    developers := List(
      Developer(
        "aslesarenko",
        "Alexander Slesarenko",
        "avslesarenko@gmail.com",
        url("https://github.com/aslesarenko")
      )
    )
  )
)

lazy val root = project
    .in(file("."))
    .settings(
      publish / skip := true,
      console        := (coreJVM / Compile / console).value,
      unusedCompileDependenciesFilter -= moduleFilter( "org.scala-js", "scalajs-library" )
    )
    .aggregate(
      coreJS,
      coreJVM,
      coreNative
    )
    .enablePlugins(ScalaJSPlugin)

lazy val core = crossProject(JSPlatform, JVMPlatform, NativePlatform)
    .in(file("core"))
    .settings(stdSettings("core"))
    .settings(crossProjectSettings)
    .settings(buildInfoSettings("cor"))
    .enablePlugins(BuildInfoPlugin)

lazy val coreJVM = core.jvm
    .settings(dottySettings)
    .settings(replSettings)
    .settings(mimaSettings(failOnProblem = true))

lazy val coreJS = core.js
    .settings(dottySettings)
    .settings(libraryDependencies += "org.scala-js" %%% "scala-js-macrotask-executor" % "1.0.0")
    .settings(
      scalacOptions ++= {
        if (scalaVersion.value == Scala3) {
          List()
        } else {
          // Temporarily disable warning to use `MacrotaskExecutor` https://github.com/zio/zio/issues/6308
          List("-P:scalajs:nowarnGlobalExecutionContext")
        }
      }
    )

lazy val coreNative = core.native
    .settings(nativeSettings)
    .settings(
      libraryDependencies ++= Seq(
        "com.github.lolgab" %%% "native-loop-core" % "0.2.1"
      )
    )
