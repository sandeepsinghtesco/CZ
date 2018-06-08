package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object Goto_Tasks {
  val goto_Tasks =
    pause(1)                                                                               // User pause
    .exec(http("Sidebar_Tasks")                                                               // Sidebar tasks click
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
        .headers(headers_2)
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r1:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r7:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r8:0:r1:0:pi1::_afrProcessState", "RUNNING")
        .formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
        .formParam("javax.faces.ViewState", "${ViewState}")
        .formParam("oracle.adf.view.rich.DELTAS", "{pt1:statusIndComp={visible=false}}")
        .formParam("event", "pt1:eachSidebarItem:2:l1")
        .formParam("event.pt1:eachSidebarItem:2:l1", """<m xmlns="http://oracle.com/richClient/comm"><k v="type"><s>action</s></k></m>""")
        .formParam("oracle.adf.view.rich.PROCESS", "f1,pt1:eachSidebarItem:2:l1")
      .check(bodyString.saveAs("Answer"))
      .check(regex("(?<=ViewState\" value=\").*?(?=\")").optional.saveAs("ViewState"))
    )
}
