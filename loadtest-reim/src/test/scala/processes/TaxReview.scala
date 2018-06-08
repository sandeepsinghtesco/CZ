package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object TaxReview {
  val taxReview =
    pause(1)                                                                                  // User pause
    .feed(datTaxReview)
    .exec(http("Search_Tax_Review")                                                           // Press search tax review button
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
      .headers(headers_2)
      .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "TaxReviewListSearchVOCriteria")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val10", "${Supplier_site}")
      .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
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

    .exec(http("Get_Tax_Review")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at1:_ATp:t1&Adf-Page-Id=2&Adf-Window-Id=w0")
        .headers(headers_2)
        .queryParam("javax.faces.ViewState", "${ViewState}")
        .queryParam("unique", cTime)
      .check(bodyString.saveAs("Answer"))
      .check(regex("(?<=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at1:_ATp:t1::emptyTxt\" class=\"x14f\">).*?(?=</div>)").optional.saveAs("Data"))
    )

    .exec((session: Session) => { 																													// Debug print
    //  println("Result:")
    //  println(session("Answer").as[String])
      print("Data: ")
      println(session("Data").asOption[String])
      session
    })
}
