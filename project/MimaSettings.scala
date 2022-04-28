import sbt._
import sbt.Keys.{organization, name}
import com.typesafe.tools.mima.plugin.MimaKeys._
import com.typesafe.tools.mima.core._
import com.typesafe.tools.mima.core.ProblemFilters._

object MimaSettings {
  lazy val bincompatVersionToCompare = "1.0.11"

  def mimaSettings(failOnProblem: Boolean) =
    Seq(
      mimaPreviousArtifacts := Set(organization.value %% name.value % bincompatVersionToCompare),
      mimaBinaryIssueFilters ++= Seq(
        exclude[Problem]("zio.internal.*"),
        exclude[Problem]("zio.ZQueue#internal#*"),
        exclude[ReversedMissingMethodProblem](
          "zio.ZManagedPlatformSpecific.zio$ZManagedPlatformSpecific$_setter_$blocking_="
        ),
        exclude[ReversedMissingMethodProblem]("zio.ZManagedPlatformSpecific.blocking"),
        exclude[ReversedMissingMethodProblem]("zio.ZIO.catchNonFatalOrDie")
      ),
      mimaFailOnProblem := failOnProblem
    )
}
