package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._


object Get_Dashboard {                                                                      // Check the dashboard and get it when ready
  var postTime = System.currentTimeMillis()
  var getTime = System.currentTimeMillis()
  var j = 0;
  var b = 0;
  val get_Dashboard =

    pause(3)

    .exec((session: Session) => {                                                          //Debug print
      cTime = System.currentTimeMillis()
      println("Start Time")
      println(cTime)
      session
    })

    .exec(http("Check_dashboard_7")
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=3")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r7:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r8:0:r1:0:pi1::_afrProcessState", "RUNNING") 
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:asyncTaskCheckPoll")
        .formParam("event.pt1:asyncTaskCheckPoll", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:asyncTaskCheckPoll")
        .check(bodyString.saveAs("Answer"))
        .check(regex("(?<=ViewState\"><!\\[CDATA\\[).*?(?=\\]></update>)").optional.saveAs("State"))
    )

    //  .exec((session: Session) => {
    //    println("State1:")
    //    println(session("State"))
    //    session
    //  })

      .asLongAs(session => session("State").as[String] != "Reim Dashboard : Retail Invoice Matching", "i") {
      pause(4)
        .exec(http("Check_loop_" + "${i}")
        .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=3")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:asyncTaskCheckPoll")
        .formParam("event.pt1:asyncTaskCheckPoll", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:asyncTaskCheckPoll")
        .check(bodyString.saveAs("LastPost"))
        )

      .exec((session: Session) => {
          cTime = System.currentTimeMillis()
          session
      })

      .exec(http("Get_dashboard_" + "${i}")                                                             // Get Dashboard
        .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:1:at1:_ATp:t1&Adf-Page-Id=3&Adf-Window-Id=w0")
        .headers(headers_1)
        .queryParam("javax.faces.ViewState", "${ViewState}")
        .queryParam("unique", cTime)
        .check(bodyString.saveAs("Answer"))
        .check(currentLocation.optional.saveAs("Page"))
        .check(regex("(?<=<eval>document.title = ')Reim Dashboard : Retail Invoice Matching(?=';</eval>)").optional.saveAs("State"))
       )
    }

      .exec(http("Finish_dash_" + j.toString)
        .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=3")
        .headers(headers_2)
     //   .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
     //   .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:asyncTaskCheckPoll")
        .formParam("event.pt1:asyncTaskCheckPoll", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:asyncTaskCheckPoll")
        .check(bodyString.saveAs("Finish"))
        //   .check(regex("(?<=<h1 class=\"xup\">)Invoices Due(?=</h1>)").optional.saveAs("State"))
      )

    .exec((session: Session) => { 																													// Debug print
    //  println("Dashboard:")
    //  println(session("Answer").as[String])
      println("State:")
      println(session("State").asOption[String])
      print("Error: ")
      println(session("Error").asOption[String])
      session
    })

    .exec((session: Session) => {                                                             // Debug print
      getTime = System.currentTimeMillis()
      println("Get Time")
      println(getTime)
      println("Diff Time")
      println(getTime - cTime)
      print("Usser: ")
      println(session("u sername").as[String]) 
      session
    })
}
