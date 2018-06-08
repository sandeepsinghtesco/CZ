package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object DocumentInv {
  val document =
    pause(1)                                                                               //  User pause
    .feed(datDocumentInv)
    .exec(http("Search_press")                                                              //Press search document button
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=3")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "DocumentSearchVOCriteria")
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val10", "${Invoice}")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("Adf-Window-Id", adfWindowId)
        .formParam("Adf-Page-Id", "1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1")
        .formParam("event.pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1", """<m xmlns="http://oracle.com/richClient/comm"><k v="clearAll"/><k v="type"><s>query</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1")
      .check(bodyString.saveAs("Answer"))
    )

    .exec((session: Session) => {
      cTime = System.currentTimeMillis()
      session
    })

    .exec(http("Search_get")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at1:_ATp:t1&Adf-Page-Id=1&Adf-Window-Id=w0")
        .headers(headers_1)
        .queryParam("javax.faces.ViewState", "${ViewState}")
        .queryParam("unique", cTime)
      .check(bodyString.saveAs("Answer"))
      .check(currentLocation.optional.saveAs("Page"))
      .check(regex("(?<=data-afr-fcs=\"true\" href=\"#\">)\\d*(?=</a>)").optional.saveAs("Document"))
    )

    .exec((session: Session) => {                                                           // Debug print
    //  println("Get_document:")
    //  println(session("Answer").as[String])
    //  println("Time")
    //  println(cTime)
    //  print("Inv number: ")
    //  println(session("Invoice").as[String])
      print("Document: ")
      println(session("Document").asOption[String])
      session
    })
}
