package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object LoginShort {
  val loginShort =
    feed(users)
    .exec(http("Get_Login")                                                                  // Start Login Page
      .get("/ReimViewController/faces/Login")
      .check(regex("(?<=\'_afrLoop\',\\s{1,3}\')\\d*(?=\')").optional.saveAs("LoopId"))
    )

    .exec(http("Get_Login_Loop")                                                            // Get Login Page
      .get("/ReimViewController/faces/Login")
        .queryParam("_afrLoop", "${LoopId}")
        .queryParam("Adf-Window-Id", adfWindowId)
      .check(regex("(?<=ViewState\" value=\").*?(?=\")").optional.saveAs("ViewState"))
      .check(regex("(?<=\'_afrLoop\',\\s{1,3}\')\\d*(?=\')").optional.saveAs("LoopId"))
      .check(bodyString.saveAs("Answer"))
    )
    .pause(1)                                                                               // User pause
    .exec(http("Post_login")                                                                // Post Login
      .post("/ReimViewController/faces/Login")
        .formParam("pt1:r1:0:login_it1", "${username}")
        .formParam("pt1:r1:0:login_it2", "${password}")
        .formParam("Adf-Window-Id", adfWindowId)
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:r1:0:login_cb1")
        .formParam("event.pt1:r1:0:login_cb1", """<m xmlns="http://oracle.com/richClient/comm"><k v="type"><s>action</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:r1,pt1:r1:0:login_cb1")
      .check(bodyString.saveAs("Answer"))
      .check(regex("AdfFacesMessage.TYPE_ERROR,\'[^)]*").optional.saveAs("Error"))
      .check(currentLocation.optional.saveAs("Page"))
    )

    .exec(http("Get_home")                                                                  // Start Home Page
      .get("/ReimViewController/faces/Home")
      .check(regex("(?<=\'_afrLoop\',\\s{1,3}\')\\d*(?=\')").optional.saveAs("LoopId"))
      .check(bodyString.saveAs("Answer"))
    )

    .exec(http("Get_home_loop")                                                             // Get Home Page
      .get("/ReimViewController/faces/Home")
        .queryParam("_afrLoop", "${LoopId}")
        .queryParam("Adf-Window-Id", adfWindowId)
      .check(regex("(?<=ViewState\" value=\").*?(?=\")").optional.saveAs("ViewState"))
      .check(bodyString.saveAs("Answer"))
    )

    .exec(http("Check_dashboard_start")                                                     // Check if the dashboard is ready
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=3")
        .headers(headers_2)
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:contentAreaPoll")
        .formParam("event.pt1:contentAreaPoll", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaPoll")
        .check(bodyString.saveAs("Answer"))
    )
}
