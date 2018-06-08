package tesco

import io.gatling.core.Predef._
import io.gatling.http.Predef._


object Processes extends BasicSimulation {

  val httpProtocol = http
    .baseURL(baseUrl)      
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-EN,en;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0")
  
   
  val headers_1 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Upgrade-Insecure-Requests" -> "1"
  ) 
  
  val headers_2 = Map(
    "Adf-Rich-Message" -> "true",
    "Adf-Window-Id" -> "w0",
    "Adf-Page-Id" -> "2",
    "Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8"
  )
  
  val headers_3 = Map(
		"Adf-Ads-Page-Id" -> "3",
		"Adf-Rich-Message" -> "true",
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"Origin" -> "http://dvrim16app001th.dev.global.tesco.org:8080")
	
	val headers_4 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")
	  
	val headers_5 = Map("Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8")
	val adfWindowId = "w0"
	
	
   //src\test\resources\data\
  val users = csv("users").circular 
  val datInvoiceMatch = csv("InvoiceMatch").circular 
  
  // Users
  val datDocumentPO = csv("DocumentPO").circular                     // Test Params
  val datDocumentInv = csv("DocumentInv").circular
  val datDocumentInvS = csv("DocumentInvS").circular
  val datDocumentSup = csv("DocumentSup").circular
  val datDocumentLoc = csv("DocumentLoc").circular
  val datCreditNote = csv("CreditNote").circular
  val datDiscrepSearch = csv("DiscrepSearch").circular
  val datTaxReview = csv("TaxReview").circular

  var cTime = System.currentTimeMillis() / 1000

}

