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

addCommandAlias("testJVM", ";coreJVM/test")
addCommandAlias("testJVM211", ";coreJVM/test")
addCommandAlias("testJS", ";coreJS/test")

lazy val scryptx = project
    .in(file("."))
    .settings(
      publish / skip := true,
//      console        := (coreJVM / Compile / console).value,
      unusedCompileDependenciesFilter -= moduleFilter( "org.scala-js", "scalajs-library" )
    )
    .aggregate(
      coreJS,
      coreJVM
    )
    .enablePlugins(ScalaJSPlugin)

lazy val core = crossProject(JSPlatform, JVMPlatform)
    .in(file("core"))
    .settings(stdSettings("core"))
    .settings(crossProjectSettings)
    .settings(buildInfoSettings("scryptx"))
    .settings(libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.2.11" % Test
    ))
    .enablePlugins(BuildInfoPlugin)

lazy val coreJVM = core.jvm
//    .settings(dottySettings)
    .settings(replSettings)
    .settings(libraryDependencies ++= Seq(
      "org.bouncycastle" % "bcprov-jdk15on" % "1.64",
      "org.scorexfoundation" %% "scrypto" % "2.1.10"))
    .settings(mimaSettings(failOnProblem = true))

lazy val coreJS = core.js
    .enablePlugins(ScalaJSBundlerPlugin/*ScalaJSPlugin auto activated*/)
//    .settings(dottySettings)
    .settings(
      scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      scalaJSUseMainModuleInitializer := true,
      Test / requireJsDomEnv := true,
      installJsdom / version := "19.0.0",
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scala-js-macrotask-executor" % "1.0.0",
        "com.raquo"    %%% "laminar" % "0.14.2"
      ),
      Compile / npmDependencies ++= Seq(
        "bn.js" -> "5.2.0",
        "hash.js" -> "1.1.7",
        "elliptic" -> "6.5.4",
        "blakejs" -> "1.2.1"
      ),
      useYarn := true
    )
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

//lazy val coreNative = core.native
//    .settings(nativeSettings)
//    .settings(
//      libraryDependencies ++= Seq(
//        "com.github.lolgab" %%% "native-loop-core" % "0.2.1"
//      )
//    )

