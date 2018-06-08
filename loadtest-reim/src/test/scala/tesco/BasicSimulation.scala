package tesco

import com.typesafe.config._
import io.gatling.core.Predef._

class BasicSimulation extends Simulation {
   val conf    = ConfigFactory.load()
   val baseUrl = conf.getString("baseUrl")
   println("Value of Base Url ::"+baseUrl);
   val env = conf.getString("environment.properties.value")
   println("Test is running on  "+env+" Environment")
  
}
