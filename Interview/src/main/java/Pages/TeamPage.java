package Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ObjectRepositories.LocatorsHelper;
import utils.GenericHelpers;

public class TeamPage {
	public WebDriver driver;
	GenericHelpers gh;
	List<String> dataEmployeeList = new ArrayList();
	List<String> engineeringEmployeeList = new ArrayList();
		
	List<WebElement> EmpNames;
	
	@FindBy(how= How.XPATH , using = LocatorsHelper.AllTabsInTeam)
	private List<WebElement> AllTabsInTeam;
	
	
		@FindBy(how= How.XPATH , using = LocatorsHelper.CountEmployeesinAllTab)
		private int CountEmployeesinAllTab;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.EmployeesNameinAllTab)
		private List<WebElement> getEmployeesNameinAllTab;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.CountEmployeesinDATATab)
		private int CountEmployeesinDATATab;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.EmployeesNameinDATATab)
		private List<WebElement> getEmployeesNameinDATATab;
		
		
		public TeamPage(WebDriver dr){
			this.driver=dr;
			gh = new GenericHelpers(driver);
			PageFactory.initElements(driver,this);
		}
		
		public int getAllEmployeeNameInAllTab() {	
			return CountEmployeesinAllTab;
		}
		
		public List getNameofDATAEmployees() {
			getEmployeesNameinDATATab.forEach(item->dataEmployeeList.add(item.getText()));
			return dataEmployeeList;
		}
		
		public List getNamesofEmployeesWrtDepartments(String deptname) {
			
		List<WebElement> EmpNames = driver.findElements(By.xpath("//div[contains(@class,'category-"+deptname+"'"+")]//div[@class='info-block']//h2"));
			
			EmpNames.forEach(item->dataEmployeeList.add(item.getText()));
			return dataEmployeeList;
			
		}
		
		public void ClickOnDataTab() {
			AllTabsInTeam.get(1).click();
		}
		public void ClickEngineeringTab() {
			AllTabsInTeam.get(2).click();	
		}
		
		public void ClickMarketingTab() {
			AllTabsInTeam.get(3).click();
		}
		public void ClickOperationsTab() {
			AllTabsInTeam.get(4).click();
		}
		public void ClickPartnerGrowthTab() {
			AllTabsInTeam.get(5).click();
		}
		public void ClickProductTab() {
			AllTabsInTeam.get(6).click();
		}
		public void ClickRecruitingTeam() {
			AllTabsInTeam.get(7).click();
		}
	
		public void ClickTab(String dept) {
			switch(dept.toLowerCase()) {
			case "data":
				ClickOnDataTab();
				break;
			case "engineering":
				ClickEngineeringTab();
				break;
			case "marketing":
				ClickMarketingTab();
				break;
			case "operations":
				ClickOperationsTab();
				break;
			case "partner growth":
				ClickPartnerGrowthTab();
				break;
			case "product":
				ClickProductTab();
				break;
			case "recruiting":
				ClickRecruitingTeam();
				break;
			default:
				System.out.println("Depatment is not available");
					
			}
		}
		
		
		public List displayEmployeeNameinCurrentTab() {
			dataEmployeeList.clear();
			EmpNames = driver.findElements(By.xpath("//div[contains(@style,'inline-block')]//div[@class='info-block']//h2"));
			EmpNames.forEach(item->dataEmployeeList.add(item.getText()));
			
			return dataEmployeeList;
		}
}
		
		
