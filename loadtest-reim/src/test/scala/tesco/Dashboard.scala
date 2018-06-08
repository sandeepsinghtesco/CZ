package tesco

import scala.concurrent.duration._

import io.gatling.core.Predef._

import tesco.Processes._


class Dashboard extends Simulation {
  val scn = scenario("Dashboard")
    .during(2400) {
      group("Dashboard Scenario") {
        group("Login") {
          exec(processes.Login.login)
        }
        .group("Get Dashboard") {
          exec(processes.Get_Dashboard.get_Dashboard)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}