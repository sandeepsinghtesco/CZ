package tesco

import io.gatling.core.Predef._
import tesco.Processes._

class Login extends Simulation {
	val scn = scenario("Login")
	 	.group("Login") {
			exec(processes.Login.login)
		}
		setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol) 
 
}