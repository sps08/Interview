package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import ObjectRepositories.LocatorsHelper;
import utils.GenericHelpers;

public class ContactPage {

	public WebDriver driver;
	GenericHelpers gh;
	
		
		@FindBy(how= How.ID , using = LocatorsHelper.ContactEmailID)
		private WebElement txtEmailID;
		
		@FindBy(how= How.ID , using = LocatorsHelper.ContactSubject)
		private WebElement txtSubject;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.ContactRequestType)
		private WebElement ddRequestType;
		
		@FindBy(how= How.XPATH , using = LocatorsHelper.ContactDescription)
		private WebElement txtDescription;
		@FindBy(how= How.NAME , using = LocatorsHelper.ContactName)
		private WebElement txtName;
		
		@FindBy(how= How.ID , using = LocatorsHelper.ContactSubmitButton)
		private WebElement btnSubmit;
		
		@FindBy(how= How.ID , using = LocatorsHelper.SuccessTicketMessage)
		private WebElement lblNotice;
		
		public ContactPage(WebDriver dr){
			this.driver=dr;
			gh = new GenericHelpers(driver);
			PageFactory.initElements(driver,this);
		}
		
		public void FillContactForm(String EmailID, String Subject, String Name, String Description, String RequestType) throws InterruptedException {
			FillContactEmailID(EmailID);
			FillContactSubject(Subject);
			FillContactName(Name);
			FillContactDescription(Description);
			SelectContactRequestTypeDropDown(RequestType);
			ClickContactSubmitButton();
			
		}
		private void FillContactEmailID(String EmailID) {
			txtEmailID.sendKeys(EmailID);
		}
		private void FillContactName(String Name) {
			
			if(txtName.isDisplayed()) {
			txtName.sendKeys(Name);
			}
		}
		private void FillContactSubject(String Subject) {
			txtSubject.sendKeys(Subject);
		}
		
		private void FillContactDescription(String Description) {
			txtDescription.sendKeys(Description);
		}
		private void SelectContactRequestTypeDropDown(String RequestType) throws InterruptedException {
			gh.scrollDownVertically();
			Thread.sleep(3000);
			
			Select oSelect =  new Select(ddRequestType);
			List <WebElement> elementCount = oSelect.getOptions();
			//int iSize = elementCount.size();
//			for(int i =0; i<iSize ; i++){
//				String sValue = elementCount.get(i).getText();
//				System.out.println(sValue);
//				}
			oSelect.selectByIndex(2);
		}
		private void ClickContactSubmitButton() {
			btnSubmit.click();
		}
		
		public String getTicketCreateMessage() {
			
			return lblNotice.getText();
		}
		
		
		
		
}
