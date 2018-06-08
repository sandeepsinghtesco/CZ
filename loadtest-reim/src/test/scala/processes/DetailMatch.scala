package processes

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import tesco.Processes._

object DetailMatch {
         
     val  DetailMatch = exec((session:Session) => {                                                          //Debug print
      cTime = System.currentTimeMillis() 
      println("Start Time of Detail Match Screen")
      println(cTime)
      session
    })
      .pause(5) 
      .exec(http("Summary Match Button Click")                                                              //Click Invoice Checkbox
    	.post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
			.headers(headers_3)
			.formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
			.formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:searchIdId", "")
			.formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1::saveSrch", "InvcRcptSearchVOCriteria")
			.formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val30", "${PO}")
			.formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:sbc3", "t")
			.formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1:0:sbc1","t")
			.formParam("pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at123456:_ATp:t2:sbc4","t")
			.formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
			.formParam("Adf-Window-Id", adfWindowId)
			.formParam("Adf-Page-Id", "2")
			.formParam("javax.faces.ViewState","${ViewState}")
			.formParam("event", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:b21")
			.formParam("event.pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:b21", """<m xmlns="http://oracle.com/richClient/comm"><k v="type"><s>action</s></k></m>""")
			.formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:b21")
			.check(regex("""(?>Invoice Summary Match<\/h1)""").exists) 
			.check(bodyString.saveAs("Answer")) 
			)
			
						
     .exec((session: Session) => {                                                             //Debug print Detail Match
      cTime = System.currentTimeMillis() 
			print("Summary Match Click Response :")
     // println(session("Answer").as[String])
      session
    })
			
		  
    .exec(http("Summary Match Button Clicked--Request2")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at142:_ATp:t2,pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1&Adf-Page-Id=2&Adf-Window-Id=w0")
			.headers(headers_4)
			.queryParam("javax.faces.ViewState", "${ViewState}")
      .queryParam("unique", cTime)
      .check(bodyString.saveAs("Answer1"))
			     
    ) 
            
     .exec((session: Session) => {                                                             // Debug print
      print("Invoice Match Response On console for second request : ")
      //println(session("Answer1").as[String])
      session
     })
    
      .exec(http("Summary Match Button Clicked--Request3")                                                              //Click Sumamry Invoice Button
			.post("/ReimViewController/faces/Home?Adf-Page-Id=2&Adf-Window-Id=w0")
			.headers(headers_3)
			.formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
			.formParam("pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1:sbc1", "t")
			.formParam("pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1:0:sbc4", "t")
			.formParam("pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at142:_ATp:t2:sbc3", "t")
			.formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
			//.formParam("Adf-Window-Id", adfWindowId)
			.formParam("Adf-Page-Id", "2")
			.formParam("javax.faces.ViewState", "${ViewState}")
			.formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1={viewportSize=19,rows=1}}")
			.formParam("oracle.adf.view.rich.STOP_ACTIVE_DATA", "pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val00,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val10,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val20,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val30,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:qryId1:val40,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at12345:_ATp:t1,pt1:contentAreaReg:0:tabrg2:2:pt1:ap1:at123456:_ATp:t2")
			.check(bodyString.saveAs("Answer2")) 
			)
			
					
			.exec((session: Session) => {                                                             // Debug print
      print("Invoice Match Response On console for third request:")
      //println(session("Answer2").as[String])
      session
      })
			
           
      .pause(20)
			
			.exec(http("Click Detail Match Button Request 1")
			.post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
			.headers(headers_3)
			.formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
			.formParam("pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1:sbc1", "t")
			.formParam("pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1:0:sbc4", "t")
			.formParam("pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at142:_ATp:t2:sbc3", "t")
			.formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
			.formParam("Adf-Window-Id", adfWindowId)
			.formParam("Adf-Page-Id", "2")
			.formParam("javax.faces.ViewState", "${ViewState}")
			.formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1={viewportSize=19,rows=1}}")
			.formParam("event", "pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:b2")
			.formParam("event.pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:b2", """<m xmlns="http://oracle.com/richClient/comm"><k v="type"><s>action</s></k></m>""")
			.formParam("oracle.adf.view.rich.PROCESS", "pt1:contentAreaReg:0:tabrg2,pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:b2")
			.check(regex("""(?>Detail Match &#8211; Item View<\/h1>)""").exists) 
			.check(bodyString.saveAs("Answer4"))
			)
			
			.exec { session =>  println("waiting for response1")
         session
       } 
						
      .exec { session =>  println(session("Answer4").as[String])
         session
       } 
			
			/*exec((session: Session) => {                                                             // Debug print
      cTime = System.currentTimeMillis() 
			print("Detail Match Button Clicked Response :")
      println(session("Answer4").as[String])
      session
      })*/
       
      			
			.exec(http("Click Detail Match Button Request 2")
      .get("/ReimViewController/faces/Home?Adf-Rich-Message=true&oracle.adf.view.rich.STREAM=pt1:contentAreaReg:0:tabrg2:4:pt1:ap1:att1:_ATTp:tt1&Adf-Page-Id=2&&Adf-Window-Id=w0")
			.headers(headers_4)
			.queryParam("javax.faces.ViewState", "${ViewState}")
      .queryParam("unique",cTime)
      .queryParam("Adf-Window-Id",adfWindowId)
			.check(bodyString.saveAs("Answer5"))
		   ) 
		  
		  .exec { session  =>  println("waiting for Answer5")
			 session 
			}
		  .exec { session =>  println(session("Answer5").as[String])
         session
       } 
		  				    
		  /*exec((session: Session) => {                                                             // Debug print
      print("Detail Match Match Button  Clicked Response :")
      println(session("Answer5").as[String])
      session
      })*/
      
      .exec(http("Click Detail Match Button Request 3")
      .post("/ReimViewController/faces/Home?Adf-Window-Id=w0&Adf-Page-Id=2")
			.headers(headers_3)
			.formParam("pt1:contentAreaReg:0:tabrg1:2:r1:0:r2:0:r1:1:r1:0:at1:_ATp:soc1", "0")
			.formParam("pt1:contentAreaReg:0:tabrg2:4:pt1:ap1:att1:_ATTp:soc1", "0")
			.formParam("org.apache.myfaces.trinidad.faces.FORM", "f1")
			.formParam("Adf-Window-Id", adfWindowId)
			.formParam("Adf-Page-Id", "2")
			.formParam("javax.faces.ViewState", "${ViewState}")
			.formParam("oracle.adf.view.rich.DELTAS", "{pt1:contentAreaReg:0:tabrg2:4:pt1:pt_r1:0:r1:0:r2:0:r1:0:barChart2={_clientWidth=262},pt1:contentAreaReg:0:tabrg2:4:pt1:pt_r1:0:r1:0:r3:0:r1:0:comboChart1={_clientWidth=262},pt1:contentAreaReg:0:tabrg2:4:pt1:ap1:att1:_ATTp:tt1={_afrLocalDiscState=,viewportSize=2,_afrVblRws=1}}")
			.formParam("event", "pt1:badgeUpdatePoll")
			.formParam("event.pt1:badgeUpdatePoll", """<m xmlns="http://oracle.com/richClient/comm"><k v="suppressMessageClear"><s>true</s></k><k v="type"><s>poll</s></k></m>""")
			.formParam("oracle.adf.view.rich.STOP_ACTIVE_DATA", "pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at1234:_ATp:t1,pt1:contentAreaReg:0:tabrg2:3:pt1:ap1:at142:_ATp:t2,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r2:0:r1:0:it1,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r2:0:r1:0:it2,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r2:0:r1:0:it4,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r2:0:r1:0:it5,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r2:0:r1:0:it3,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r1:0:r1:0:QtyLineChart1,pt1:contentAreaReg:0:tabrg2:3:pt1:pt_r1:0:r1:0:r1:0:r1:0:costLineChart1")
			.formParam("oracle.adf.view.rich.PROCESS", "pt1:badgeUpdatePoll")
			.check(bodyString.saveAs("Answer6"))
			)
			
			 .exec { session  =>  println("waiting for Answer6")
			 session 
			}
		  .exec { session =>  println(session("Answer6").as[String])
         session
       } 
		  		
		  
			/* exec((session: Session) => {                                                             //Debug print
       print("Answer 6 :")
       println(session("Answer6").as[String])
       session
      })*/
				
    
}
