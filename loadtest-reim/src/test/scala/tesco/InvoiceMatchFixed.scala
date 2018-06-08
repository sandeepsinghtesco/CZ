package tesco

import io.gatling.core.Predef._
import tesco.Processes._

import scala.concurrent.duration._


class InvoiceMatchFixed extends Simulation {
  val scn = scenario("Invoice Matching")
    .group("Invoice Matching Scenario"){
      group("Login") {
        exec(processes.LoginShort.loginShort)
        .exec(processes.Goto_Tasks.goto_Tasks)
        .exec(processes.Goto_InvMatch.goto_InvMatch)
      }
      .during(1200) {
        pause(1)
        .group("Invoice Match") {
          exec(processes.InvoiceMatch.invoiceMatch)
        }
      }
      .group("LogOut") {
        exec(processes.LogOut.logOut)
      }
    }

  setUp(scn.inject(rampUsers(10) over (60 seconds))
  ).protocols(httpProtocol)
}