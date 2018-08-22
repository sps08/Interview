package testsuites;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.AfterMethod;

import Browsers.BrowserAdapter;
import utils.ExtentManager;
import utils.FrameworkConfig;

public class TestBase  {
	WebDriver driver;
	public ExtentReports reports=ExtentManager.getInstance();
	public ExtentTest extentlogger;
	String TCName;
    ITestResult result;
    
    public TestBase() {
    	
    }
//	public TestBase(WebDriver driver, ExtentTest extentLogger) {
//		this.driver=driver;
//		this.extentlogger=extentLogger;
//	}
	
	public WebDriver initBrowser(String _browser) {
		return new BrowserAdapter().initializeBrowserAdapter(_browser);
	}
	
	@BeforeSuite
	public void setup() {
		driver = initBrowser(FrameworkConfig.getPropertyMap().get("browser"));
	}
	
	@BeforeMethod
	public void  beforeMethod(Method method) {
		 Test test = method.getDeclaredAnnotation(Test.class);
		//TCName = this.getClass().getSimpleName() +"-"+ method.getName();
		 TCName = "Branch - " +method.getName();
		extentlogger = reports.startTest(TCName);
		extentlogger.log(LogStatus.INFO,"<b>METHOD NAME: </b>"+ method.getName());
		//System.out.println("in before method");
		extentlogger.log(LogStatus.INFO,"<b>DESCRIPTION: </b>" + test.description() +"  is Running");
	}
	
	@AfterMethod
	public void AfterTest(ITestResult result) {
		//System.out.println("in after method");

		    switch (result.getStatus()) {
		    case ITestResult.SUCCESS:
		       // System.out.println("======PASS=====");
		        extentlogger.log(LogStatus.PASS, result.getMethod().getMethodName() + "---  Passed");
		        break;

		    case ITestResult.FAILURE:
		        //System.out.println("======FAIL=====");
		        extentlogger.log(LogStatus.FAIL, result.getMethod().getMethodName() + "-- Failed");
		        break;

		    case ITestResult.SKIP:
		       // System.out.println("======SKIP BLOCKED=====");
		        extentlogger.log(LogStatus.SKIP, result.getMethod().getMethodName() + "-- Skipped");
		        break;

		    default:
		        throw new RuntimeException("Invalid status");
		    }
		    reports.endTest(extentlogger);
		    
	}
	
	@AfterSuite
	public void teardown() throws IOException {
	reports.flush();
	Runtime runTime = Runtime.getRuntime();
	System.out.println("get");
	System.out.println(System.getProperty("user.dir")+"/BranchAutomationResults.html");
    Process process = runTime.exec(System.getProperty("user.dir")+"/BranchAutomationResults.html");
		
		 if (driver != null) {
	          try {
	        	 driver.close();
	          } finally {
	             driver.quit(); 
	          }
	        }    

	}

	
}
