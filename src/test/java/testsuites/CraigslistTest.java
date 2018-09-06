package testsuites;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AUTH;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;



import Pages.CraigslistPage;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CraigslistTest extends TestBase {
	CraigslistPage cp;
	String searchword ="bmw";
	
	
	@Test(priority=1, description="Verify the search is saved or not")
	public void VerifyTheCraigsListSaveFunctionality_Webdriver() throws InterruptedException {
		cp =  new CraigslistPage(driver);
		//cp.LoginToCraigsList();
		
		driver.get("https://sfbay.craigslist.org/");
		cp.searchAbout(searchword);
		cp.goToSecondPageofCLResults();
		cp.SearchItemsDisplayedOnScreen();
		cp.SaveTheCurrentSearch();
		cp.LoginToCraigsList();
		
		Assert.assertEquals("Your "+'"'+searchword+'"'+" search has been saved.",cp.getLabelAfterSearchSave());
		
	}
	
	@Test(priority=2, description="Verify the search is saved or not")
	public void VerifyTheCraigsListDeleteFunctionality_HTTP() throws InterruptedException {
		
//		
//		Map<String,String> cookiesmap = new HashMap<String,String>();
//		cookiesmap.put("cl_def_hp","sfbay");
//		cookiesmap.put("cl_b","4ldOTcyq6BG9kmcdwOpafw1CejU");
//		cookiesmap.put("cl_tocmode","sss%3Agrid");
//		cookiesmap.put("cl_session","K5zRPnxD1ysDb6lLbiPVlwtlD04a7uAw8sqrsYEFHmaXlb7tfcOEuGKxjuVhEgr1");
		
	RestAssured.baseURI ="https://accounts.craigslist.org";
//	Response res = given().log().all()
//			.contentType("application/x-www-form-urlencoded")
//			.when()
//			.get("/login")
//			.then()
//			.statusCode(200).extract().response();
//		System.out.println(res.headers());
//		
	
	FormAuthConfig fa = new FormAuthConfig("https://accounts.craigslist.org/login", "inputEmailHandle", "inputPassword");
    System.out.println(fa.getFormAction());
	
		Response response = given().redirects().follow(false).log().all()
		//.auth().preemptive().basic("satinderworks@gmail.com", "Mycode@Mycode")
		 .auth().form("satinderworks@gmail.com", "Mycode@Mycode" ,FormAuthConfig.formAuthConfig().springSecurity())
		.when().get("/login/home").then().statusCode(200).extract().response();
		
	//System.out.println(response.getStatusCode());
	System.out.println(response.getSessionId());
		
		
	}
}
