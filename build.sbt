lazy val rootSettings = Seq(
  organization := "sbt.error.test",
  scalaVersion := "2.13.3",
  updateOptions := updateOptions.value.withGigahorse(false),
  fork := true,
  name := "sbt-error-test",
  artifact in (Compile, assembly) := {
    val art = (artifact in (Compile, assembly)).value
    art.withClassifier(Some("assembly"))
  }
)

lazy val assemblySettings = Seq(
  mainClass in assembly := Some("sbt.error.test.Main")
)

lazy val publishSettings = Seq(
  credentials += Nexus.credentials,
  publishTo := {
    if (isSnapshot.value)
      Some(
        ("snapshots" at Nexus.snaphotsRepo).withAllowInsecureProtocol(true)
      )
    else
      Some(
        ("releases" at Nexus.releasesRepo).withAllowInsecureProtocol(true)
      )
  }
)

lazy val addArtifactSettings = {
  val assembled = Compile / assembly / artifact

  addArtifact(assembled, assembly).settings
}

lazy val root = project
  .in(file("."))
  .settings(rootSettings)
  .settings(assemblySettings)
  .settings(publishSettings)
  .settings(addArtifactSettings)
