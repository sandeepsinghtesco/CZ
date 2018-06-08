package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object Goto_DiscrepSearch {
  val goto_DiscrepSearch =
    pause(1)                                                                                // User pause
    .exec(http("Sidebar_Invoice")                                                            // Sidebar invoice matching click
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r8:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg1:2:r1:0:r7:0:r1:1:statusMeterGauge1={_clientHeight=96,_clientWidth=55},pt1:contentAreaReg:0:tabrg1:2:r1:0:r7:0:r1:1:statusMeterGauge2={_clientHeight=96,_clientWidth=55},pt1:statusIndComp={visible=false}}")
        .formParam("event", "pt1:showMenuPanelDynRegion:1:taskItr:2:l9")
        .formParam("event.pt1:showMenuPanelDynRegion:1:taskItr:2:l9", """<m xmlns="http://oracle.com/richClient/comm"><k v="type"><s>action</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:showMenuPanelDynRegion,pt1:showMenuPanelDynRegion:1:taskItr:2:l9")
      .check(bodyString.saveAs("Answer"))
    )

    .pause(1)                                                                               // User pause
    .exec(http("Sidebar_Discrepancy_Review")                                                // Sidebar discrepancy review list click
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r8:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("oracle.adf.view.rich.DELTAS", "{pt1:statusIndComp={visible=true}}")
        .formParam("event", "pt1:showMenuPanelDynRegion:1:taskItr:2:l6")
        .formParam("event.pt1:showMenuPanelDynRegion:1:taskItr:2:l6", """<m xmlns="http://oracle.com/richClient/comm"><k v="type"><s>action</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:showMenuPanelDynRegion,pt1:showMenuPanelDynRegion:1:taskItr:2:l6")
      .check(bodyString.saveAs("Answer"))
    )

    .exec(http("Sidebar_Discrepancy_Review_Post")
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r8:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg2:1:xp1={interval=-1}}")
        .formParam("event", "pt1:contentAreaReg:0:tabrg2:1:xp1")
        .formParam("event.pt1:contentAreaReg:0:tabrg2:1:xp1", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2:1:xp1")
      .check(bodyString.saveAs("Answer"))
    )
}
