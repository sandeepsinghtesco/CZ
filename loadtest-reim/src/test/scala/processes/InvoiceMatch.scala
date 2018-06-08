package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object InvoiceMatch {
  var postTime = System.currentTimeMillis()
  var getTime = System.currentTimeMillis()
  
  val invoiceMatch =
    feed(datInvoiceMatch)
    .exec((session: Session) => {                                                          // Debug print
      cTime = System.currentTimeMillis()
      println("Start Time")
      println(cTime)
      session
    })

    .exec(http("Search_Invoice")                                                              // Press search invoice button
        .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r8:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "InvcRcptSearchVOCriteria")
        .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val30", "${PO}")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("event", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1")
        .formParam("event.pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1", """<m xmlns="http://oracle.com/richClient/comm"><k v="clearAll"/><k v="type"><s>query</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1")
        .check(bodyString.saveAs("Answer")) 
    )

    .exec((session: Session) => {                                                           // Debug print
      postTime = System.currentTimeMillis() 
      println("Post Time")
      println(postTime)
      if (postTime - cTime > 20000) {
        println("User:")
        println(session("username").as[String])
        println(session("Answer").as[String])
        println("Long time")
        println("Post_document:")
        println(session("Answer").as[String])
      }
      session
    })

    .exec(session => session.set("Inv","None"))
    .exec(session => session.set("Data","None"))

    .exec(http("Get_Invoice")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at123456:_ATp:t2&Adf-Page-Id=3&Adf-Window-Id=w0")
        .headers(headers_2)
        .queryParam("javax.faces.ViewState", "${ViewState}")
        .queryParam("unique", cTime)
      .check(bodyString.saveAs("Answer"))
      .check(regex("(?<=nowrap role=\"rowheader\" id=\"pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp).*?(?=</td>)").optional.saveAs("Inv"))
      .check(regex("(?<=xt\" class=).*?(?=</div>)").optional.saveAs("Data"))
    )

    .exec((session: Session) => {                                                             // Debug print
      getTime = System.currentTimeMillis()
      println("Get Time")
      println(getTime)
      println("Diff Time")
      println(getTime - cTime)
      print("Invoice Match result: ")
      println(session("username").as[String])
      println(session("PO").as[String])
      print("Invoice result: ")
      println(session("Inv").asOption[String])
      print("Data result: ")
      println(session("Data").as[String])
      if (session("Data").as[String] == "None" && session("Inv").as[String] == "None") {
        println("Error")
        println("Get_document:")
        println(session("Answer").as[String])
      }
      session
    })
}
