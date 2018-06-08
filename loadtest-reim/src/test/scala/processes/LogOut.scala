package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._


object LogOut {                                                                      // Check the dashboard and get it when ready
  val logOut =

  exec((session: Session) => {
    cTime = System.currentTimeMillis()
    session
  })

  .exec(http("Get Logout")
    .get("/ReimViewController/adfAuthentication?logout=true&end_url=/faces/Home")
    .headers(headers_1)
    .queryParam("javax.faces.ViewState", "${ViewState}")
    .queryParam("unique", cTime)
    .check(bodyString.optional.saveAs("Out"))
    .check(currentLocation.optional.saveAs("Page"))
  )

    .exec((session: Session) => {
    //  println("Logout Answer:")
    //  println(session("Out").asOption[String])
      print("Page: ")
      println(session("Page").asOption[String])
      //	println("Redirect:")
      //	println(session("Redirect").asOption[String])
      session
    })
}
