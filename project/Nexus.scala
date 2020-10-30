import sbt.Credentials

object Nexus {

  lazy val username: String = "xxxx"
  lazy val password: String = "xxxx"
  lazy val url: String      = "http://your.nexus"
  lazy val credentials      = Credentials("Sonatype Nexus Repository Manager", "your.nexus", username, password)
  lazy val snaphotsRepo = Nexus.url + "/nexus/repository/snapshots/"
  lazy val releasesRepo = Nexus.url + "/nexus/repository/releases/"

}
