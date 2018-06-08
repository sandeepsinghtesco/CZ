package tesco

import scala.concurrent.duration._

import io.gatling.core.Predef._

import tesco.Processes._


class DiscrepSearch extends Simulation {
  val scn = scenario("Discrepancy Search")
    .during(2400) {
      group("Discrepancy Search Scenario") {
        group("Login") {
          exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_DiscrepSearch.goto_DiscrepSearch)
        }
        .group("Search Discrepancy") {
          exec(processes.DiscrepSearch.discrepSearch)
        }
        .group("LogOut") {
          exec(processes.LogOut.logOut)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}
