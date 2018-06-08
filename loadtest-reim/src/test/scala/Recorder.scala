import io.gatling.recorder.GatlingRecorder
import io.gatling.recorder.config.RecorderPropertiesBuilder

object Recorder extends App {

	val props = new RecorderPropertiesBuilder
	props.simulationOutputFolder(IDEPathHelper.recorderOutputDirectory.toString)
	props.simulationPackage("tesco")
	props.bodiesFolder(IDEPathHelper.bodiesDirectory.toString)
	GatlingRecorder.fromMap(props.build, Some(IDEPathHelper.recorderConfigFile))
}
