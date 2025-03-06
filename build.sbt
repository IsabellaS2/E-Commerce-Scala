import org.scalajs.linker.interface.ModuleSplitStyle

val CirceVersion = "0.14.5"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "ui-testing",
    scalaVersion := "3.3.3",
    scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature", "-no-indent"),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("example")))
        .withESFeatures(_.withAvoidClasses(false))
    },
    libraryDependencies ++= Seq(
      "com.raquo" %%% "laminar" % "16.0.0",
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "com.github.japgolly.scalacss" %%% "core" % "1.0.0",
      "io.frontroute" %%% "frontroute" % "0.19.0",
      "io.circe" %%% "circe-generic" % CirceVersion,
      "io.circe" %%% "circe-core" % CirceVersion,
      "io.circe" %%% "circe-parser" % CirceVersion,
      "io.circe" %%% "circe-literal" % CirceVersion
    )
  )
