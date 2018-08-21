package testsuites;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import Pages.BranchHomePage;
import Pages.ContactPage;
import Pages.GooglePage;
import Pages.TeamPage;


public class BranchHomePageTests extends TestBase{
	GooglePage gp ;
	BranchHomePage bhp;
	TeamPage tp;
	ContactPage cp;

	int AllOthersDeptsCount = 0;
	int AllDeptCount =0;
	List MasterList = new ArrayList();
	List AllList = new ArrayList();
	
	List DataEmployeesAll = null;
	String TCName;
	
	
	@Test(priority=0, description=" Open the Google website and Searcg Branch Company Name")
	public void OpenGoogleWebSiteAndSearchCompanyURL() {
		gp =  new GooglePage(driver);
		gp.getGoogleUrl();
		gp.SearchCompanyNameonGoogle("Branch.io");
	}
	
	@Test(priority=1, description=" Click on Branch Company and Navigate to Branch company Website")
	public void NavigateToCompanyUrl() {
		gp.ClickCompanyLinkFromGoogleResults();
	}
	
	@Test(priority=2, description="Click on Team link available on footer")
	public void ClickonTeamLink() {
		bhp = new BranchHomePage(driver);
		bhp.ClickOnFooterCompanyTEAMLink();
	}
	
	@Test(priority=3, description="In All Tabs get All the Employeee Count and Employee Names")
	public void getNamesOfEmployeesInAllDept() {
		tp = new TeamPage(driver);
		DataEmployeesAll = tp.getNamesofEmployeesWrtDepartments("all");
		AllDeptCount = DataEmployeesAll.size();
		AllList.addAll(DataEmployeesAll);
		extentlogger.log(LogStatus.INFO, "<b>All Tab employee Names are </b> --->"+ DataEmployeesAll);
		extentlogger.log(LogStatus.INFO, "<b>Total No of Employees in ALL tab</b> = "+ AllDeptCount);		
	}
	
	  @DataProvider
	  public static Object[][] MyTabsCollection() {
	 
	        return new Object[][] { {"data"},{"engineering"},{"marketing"},{"operations"},{"partner growth"},{"product"},{"recruiting"} };
	 
	  }
	  
	@Test(priority = 6 , dataProvider="MyTabsCollection", description="Click on All the Depatments and count no of Employees and employee Names in each Department")
	public void ClickOnEveryDeptTabAndFetchTotalNoOfEmployees(String dept) {
		tp = new TeamPage(driver);
		tp.ClickTab(dept);
		List DataEmployeesInCurrentTab  = tp.displayEmployeeNameinCurrentTab();
		AllOthersDeptsCount+=DataEmployeesInCurrentTab.size();
		
		extentlogger.log(LogStatus.INFO,  "<b>"+ dept.toUpperCase() + "  Employee Names are</b> ----->"+ DataEmployeesInCurrentTab);
		extentlogger.log(LogStatus.INFO, "  <b>Total No of Employees</b> = "+ DataEmployeesInCurrentTab.size());
		MasterList.addAll(DataEmployeesInCurrentTab);
	}
	
	@Test(priority=7 ,dependsOnMethods={"getNamesOfEmployeesInAllDept","ClickOnEveryDeptTabAndFetchTotalNoOfEmployees"}, description="Compare the No of Employees in All tab and Other Dept tabs")
	public void IsCountinAllTabAndOthersTabsEqual() {
		extentlogger.log(LogStatus.INFO, "<b>No of Employees in 'ALL' tab   "+AllList.size() +"  and No of employees in 'Other Tabs'  " + MasterList.size() );
		Assert.assertEquals(AllDeptCount, AllOthersDeptsCount);
		
	}
	
	@Test(priority=8 ,dependsOnMethods={"getNamesOfEmployeesInAllDept","ClickOnEveryDeptTabAndFetchTotalNoOfEmployees"}, description ="Compare the Number of employees in All tabs and Other departments")
	public void IsNamesinAllTabsAndOthersTabsEqual() {
		if(AllList.equals(MasterList)) {
			Assert.assertTrue(true);
			extentlogger.log(LogStatus.INFO,"<b>All department and Other Department has same No of employees</b>");
			
		}else {
			AllList.removeAll(MasterList);
			extentlogger.log(LogStatus.INFO,"<b>"+AllList.toString() + " are not assigned to any department</b>");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(AllList, MasterList);
		
	}
	
	//@Test(priority=7)
	public void checkMasterList() {
		MasterList.forEach(items->System.out.println("masterlist is----> "+ items));
		AllList.forEach(items->System.out.println("All list is----> "+ items));
		AllList.removeAll(MasterList);
		AllList.forEach(items->System.out.println("Extra--->  "+items));
		
	}

	
	@Test(priority=9,alwaysRun = true)
	@Parameters({"EmailID","Description","RequestType","Subject","Name"})
	public void FillContactPageForDemo(String EmailID, String Subject,String Name, String Description, String RequestType) throws InterruptedException {
		bhp.ClickOnFooterCompanyCONTACTLink();
		cp = new ContactPage(driver);
		
		cp.FillContactForm(EmailID, Subject,Name, Description, RequestType);
		if(cp.getTicketCreateMessage().contains("Your ticket has been created.")) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}

		//Assert.assertEquals(cp.getTicketCreateMessage(),"Your ticket has been created.");
	}
}
	
	
	
	

