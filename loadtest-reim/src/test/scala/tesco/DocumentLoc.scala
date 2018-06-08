package tesco

import io.gatling.core.Predef._
import tesco.Processes._

import scala.concurrent.duration._


class DocumentLoc extends Simulation {
  val scn = scenario("Search Locations")
    .during(2400) {
      group("Document Scenario") {
        group("Login") {
          exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_Document.goto_Document)
        }
        .group("Search Document") {
          exec(processes.DocumentLoc.document)
        }
        .group("LogOut") {
          exec(processes.LogOut.logOut)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}
