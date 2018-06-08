package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object SummaryMatch {

  val SummaryMatch = exec((session: Session) => {   // Debug print
    cTime = System.currentTimeMillis()
    println("Start Time of Sumamry Match Screen")
    println(cTime)
    session
    })

    .exec(http("Select Invoice") //Click Invoice Checkbox
      .post("/ReimViewController/faces/Home?Adf-Page-Id=2")
      .headers(headers_3)
      .queryParam("Adf-Window-Id", adfWindowId)
      .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
      .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:searchIdId", "")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "InvcRcptSearchVOCriteria")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val30", "${PO}")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:sbc3", "t")
      .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
      .formParam("Adf-Window-Id", adfWindowId)
      .formParam("Adf-Page-Id", "2")
      .formParam("javax.faces.ViewState", "${ViewState}")
      .formParam("event", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:sbc3")
      .formParam("event.pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:sbc3", """<m xmlns="http://oracle.com/richClient/comm"><k v="autoSubmit"><b>1</b></k><k v="suppressMessageShow"><s>true</s></k><k v="type"><s>valueChange</s></k></m>""")
      .formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:sbc3")
      .check(bodyString.saveAs("Answer")))

    .exec((session: Session) => { //Debug print
      print("Invoice Match Response for  first request for checked all invoice: ")
      println(session("Answer").as[String])
      session
    })

    .exec(session => session.set("IsChecked","None"))
    
    .pause(5)
    
    .exec(http("Click_Invoice_chekced")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1&Adf-Page-Id=1")
      .headers(headers_1)
      .queryParam("javax.faces.ViewState", "${ViewState}")
      .queryParam("unique", cTime)
      .queryParam("Adf-Window-Id", adfWindowId)
      .check(regex("""(?>\"xu4\"*.\stype=\"checkbox\"\svalue=\"t\" checked>)""").exists)
      .check(regex("""(?>\"xu4\"*.\stype=\"checkbox\"\svalue=\"t\" checked>)""").optional.saveAs("IsChecked"))
      .check(bodyString.saveAs("Answer2"))
      )

    .exec((session: Session) => { // Debug print
      print("Invoice Match Response On console for second request : ")
      println(session("Answer2").as[String])
      print("Value of Checked : ")
      println(session("IsChecked").as[String])
      session
    })
     


    .exec(http("Select Invoice_post") //Click Invoice Checkbox
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
      .headers(headers_3)
      .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "InvcRcptSearchVOCriteria")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val30", "${PO}")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:sbc3", "t")
      .formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:0:sbc1", "t")
      .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
      .formParam("Adf-Window-Id", adfWindowId)
      .formParam("Adf-Page-Id", "2")
      .formParam("javax.faces.ViewState", "${ViewState}")
      .formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1={viewportSize=11}}")
      .formParam("event", "pt1:badgeUpdatePoll")
      .formParam("event.pt1:badgeUpdatePoll", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
      .formParam("oracle.adf.view.rich.PROCESS", "pt1:badgeUpdatePoll")
      .check(bodyString.saveAs("Answer3"))) 
      
      
      
      
      .exec((session: Session) => { // Debug print
      print("Invoice Match Response On console for third request : ")
      println(session("Answer3").as[String])
      session
    })



}
