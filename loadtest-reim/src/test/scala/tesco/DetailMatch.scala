package tesco

import scala.concurrent.duration._
import io.gatling.core.Predef._
import tesco.Processes._

class DetailMatch extends Simulation  {
  val scn = scenario("Summary Invoice Matching")
    .repeat (1) {
      group("Detail Invoice Matching Scenario"){
        group("Login") {
           exec(processes.Login.login)
          .exec(processes.Goto_Tasks.goto_Tasks)
          .exec(processes.Goto_InvMatch.goto_InvMatch)
        }
        .group("Invoice Match") {
          exec(processes.InvoiceMatch.invoiceMatch)
        }
        .group("Summary Match") {
          exec(processes.SummaryMatch.SummaryMatch)
        }
        .group("Detail Match") {
          exec(processes.DetailMatch.DetailMatch)
        }
        
      }
    }
    setUp(scn.inject(rampUsers(3) over (10 seconds))
       
  ).protocols(httpProtocol)
}
