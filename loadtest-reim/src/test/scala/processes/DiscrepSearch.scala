package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object DiscrepSearch {
  val discrepSearch =
    pause(1)                                                                               // User pause
    .feed(datDiscrepSearch)
    .exec(http("Search_Discrepancy_Review_List")                                            // Press search discrepancy list button
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "DiscrepancyListSearchVOCriteria")
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val20", "${Supplier_site}")
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val30", "${AP_reviewer}")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg2:2:pt1:pt_r1:0:r1:0:r2:0:r1:0:barChart2={_clientWidth=262},pt1:contentAreaReg:0:tabrg2:2:pt1:pt_r1:0:r1:0:r3:0:r1:0:comboChart1={_clientWidth=262}}")
        .formParam("event", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1")
        .formParam("event.pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1", """<m xmlns="http://oracle.com/richClient/comm"><k v="clearAll"/><k v="type"><s>query</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1")
      .check(bodyString.saveAs("Answer"))
    )

    .exec((session: Session) => {
      cTime = System.currentTimeMillis()
      session
    })

    .exec(http("Get_Discrepancy")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1&Adf-Page-Id=2&Adf-Window-Id=w0")
        .headers(headers_2)
        .queryParam("javax.faces.ViewState", "${ViewState}")
        .queryParam("unique", cTime)
      .check(bodyString.saveAs("Answer"))
      .check(regex("(?<=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1::emptyTxt\" class=\"x14f\">).*?(?=</div>)").optional.saveAs("Data"))
    )

    .exec((session: Session) => { 																													    // Debug print
    //  println("Result:")
    //  println(session("Answer").as[String])
      print("Data: ")
      println(session("Data").asOption[String])
      session
    })
}
