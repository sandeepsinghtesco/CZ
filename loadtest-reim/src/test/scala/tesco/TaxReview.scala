package tesco

import scala.concurrent.duration._

import io.gatling.core.Predef._

import tesco.Processes._


class TaxReview extends Simulation {
  val scn = scenario("Tax_Review")
    .during(2400) {
      group("Tax Review Scenario") {
        group("Login") {
          exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_TaxReview.goto_TaxReview)
        }
        .group("Search Tax Review") {
          exec(processes.TaxReview.taxReview)
        }
        .group("LogOut") {
          exec(processes.LogOut.logOut)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}
