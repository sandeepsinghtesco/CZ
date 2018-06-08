package tesco

import scala.concurrent.duration._

import io.gatling.core.Predef._

import tesco.Processes._


class DocumentSup extends Simulation {
  val scn = scenario("Search Supplier Sites")
    .during(2400) {
      group("Document Scenario"){
        group("Login") {
           exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_Document.goto_Document)
        }
        .group("Search Document") {
          exec(processes.DocumentSup.document)
        }
        .group("LogOut") {
          exec(processes.LogOut.logOut)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}
