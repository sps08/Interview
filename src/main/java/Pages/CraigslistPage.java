package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ObjectRepositories.LocatorsHelper;
import utils.GenericHelpers;

public class CraigslistPage {

	
	public WebDriver driver;
	GenericHelpers gh;
	
	public CraigslistPage(WebDriver dr){
		this.driver=dr;
		gh = new GenericHelpers(driver);
		PageFactory.initElements(driver,this);
	}
		
		@FindBy(how= How.LINK_TEXT , using = LocatorsHelper.CL_login)
		private WebElement lnkMyAccount;
		
		@FindBy(how= How.ID , using = LocatorsHelper.CL_username)
		private WebElement txtusername;
		
		@FindBy(how= How.ID , using = LocatorsHelper.CL_password)
		private WebElement txtpassword;
		
		@FindBy(how= How.CLASS_NAME , using = LocatorsHelper.CL_submitbtn)
		private WebElement btnsubmit;
		
		@FindBy(how= How.ID , using = LocatorsHelper.CL_query)
		private WebElement txtquery;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.CL_NextPage)
		private WebElement nextbtn;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.CL_SearchList)
		private List<WebElement> NoOfSearchResults;
		
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.CL_SearchListNames)
		private List<WebElement> NamesOfSearchedItems;
		
		@FindBy(how= How.CLASS_NAME , using = LocatorsHelper.CL_saveSearch)
		private WebElement saveSearch;
		
		
		@FindBy(how= How.XPATH, using = LocatorsHelper.CL_LblSavedSearch)
		private WebElement LblSavedSearch;
		

		
		public void LoginToCraigsList() {
			//lnkMyAccount.click();
			txtusername.sendKeys("satinderworks@gmail.com");
			txtpassword.sendKeys("Mycode@Mycode");
			btnsubmit.click();
		}		
		
		public void searchAbout(String searchWord) {
			txtquery.sendKeys(searchWord);
			txtquery.sendKeys(Keys.RETURN);
		}

		public void goToSecondPageofCLResults() throws InterruptedException {
			Thread.sleep(4000);
			nextbtn.click();	
		}
		
		public int  getNumberofSearchResults() {
		 return NoOfSearchResults.size();
			
		}
		public void SearchItemsDisplayedOnScreen() {
			
			NamesOfSearchedItems.forEach(item -> System.out.println(item.getText()));
		}
		public void SaveTheCurrentSearch() {
			saveSearch.click();
			
			
		}
		public String getLabelAfterSearchSave() throws InterruptedException {
			
			return LblSavedSearch.getText();
		}
}
