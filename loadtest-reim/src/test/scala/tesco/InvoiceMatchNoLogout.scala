package tesco

import io.gatling.core.Predef._
import tesco.Processes._

import scala.concurrent.duration._


class InvoiceMatchNoLogout extends Simulation {
  val scn = scenario("Invoice Matching")
    .during (2400) {
      group("Invoice Matching Scenario"){
        group("Login") {
          exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_InvMatch.goto_InvMatch)
        }
        .group("Invoice Match") {
          exec(processes.InvoiceMatch.invoiceMatch)
        }
      }
    }

  setUp(scn.inject(rampUsers(200) over (1200 seconds))
  ).protocols(httpProtocol)
}
