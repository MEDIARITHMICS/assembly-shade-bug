import sbtassembly.ShadeRule



scalaVersion := "2.10.6"

name := "assembly-shade-bug"

libraryDependencies += "org.parboiled" %% "parboiled" % "2.0.1"

assemblyOption in assembly ~= { _.copy(includeScala = false) }

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.fasterxml.jackson.**" -> "mcs.com.fasterxml.jackson.@1").inAll
)

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)


