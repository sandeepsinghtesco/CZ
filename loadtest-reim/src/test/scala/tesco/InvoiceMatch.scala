package tesco

import scala.concurrent.duration._
import io.gatling.core.Predef._
import tesco.Processes._


class InvoiceMatch extends Simulation {
  val scn = scenario("Invoice Matching")
    .during (100) {
      group("Invoice Matching Scenario"){
         group("Login") {
           exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_InvMatch.goto_InvMatch)
        }
        .group("Invoice Match") {
          exec(processes.InvoiceMatch.invoiceMatch)
        }
        .group("LogOut") {
          exec(processes.LogOut.logOut)
        }
      }
    }

  setUp(scn.inject(rampUsers(1) over (20 seconds))
  ).protocols(httpProtocol)
}
