package tesco

import scala.concurrent.duration._

import io.gatling.core.Predef._

import tesco.Processes._


class CreditNote extends Simulation {
  val scn = scenario("Invoice")
    .during(2400) {
      group ("Credit Note Scenario") {
        group("Login") {
          exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_CreditNote.goto_CreditNote)
          }
        .group("Search Credit Note") {
          exec(processes.CreditNote.creditNote)
        }
        .group("LogOut") {
          exec(processes.LogOut.logOut)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}