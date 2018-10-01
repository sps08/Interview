package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ObjectRepositories.LocatorsRepo;
import utils.GenericHelpers;

public class LivongoHomePage {
	
	public WebDriver driver;
	GenericHelpers gh;
	
	public LivongoHomePage(WebDriver dr){
		this.driver=dr;
		gh = new GenericHelpers(driver);
		PageFactory.initElements(driver,this);
	}
		
		@FindBy(how= How.XPATH , using = LocatorsRepo.Home_JoinNow)
		private WebElement btnJoinNow;
		
		@FindBy(how= How.ID , using = LocatorsRepo.Home_FirstName)
		private WebElement txtLegalFirstName;
		@FindBy(how= How.ID , using = LocatorsRepo.Home_LastName)
		private WebElement txtLegalLastName;
		
		@FindBy(how= How.NAME , using = LocatorsRepo.Home_MonthDOB)
		private WebElement txtmonth;
		@FindBy(how= How.NAME , using = LocatorsRepo.Home_DayDOB)
		private WebElement txtday;
		@FindBy(how= How.NAME , using = LocatorsRepo.Home_YearDOB)
		private WebElement txtyear;
	
		
		@FindBy(how= How.ID , using = LocatorsRepo.Home_UserName)
		private WebElement txtUserName;
		@FindBy(how= How.ID , using = LocatorsRepo.Home_Password)
		private WebElement txtPassword;
		@FindBy(how= How.ID , using = LocatorsRepo.Home_TermsandCondition)
		private WebElement ckbTermsCondition;
		
		@FindBy(how= How.CLASS_NAME , using = LocatorsRepo.Home_Submitbtn)
		private WebElement btnSubmit;
		
		
		public void clickJoinNowButton() {
			btnJoinNow.click();
		}
		public void EnterAlltheValueonHomePage(String Fname, String Lname, String month, String day, String year, String username, String password) throws InterruptedException {
			EnterLegalFirstName(Fname);
			EnterLegalLastName(Lname);
			EnterDOB(month, day, year);
			EnterUserNameAndPassword(username, password);
			ClickTermsAndConditionCheckbox();
			HomePageClickSubmitButton();
			
		}
		
		private void EnterLegalFirstName(String Fname) {
			txtLegalFirstName.sendKeys(Fname);
		}
		private void EnterLegalLastName(String Lname) {
			txtLegalLastName.sendKeys(Lname);
		}
		private void EnterDOB(String month, String day, String year) throws InterruptedException {
			txtmonth.sendKeys(month);
			txtday.sendKeys(day);
			txtyear.sendKeys(year);
		}
		
		private void EnterUserNameAndPassword(String username, String password) {
			txtUserName.sendKeys(username);
			txtPassword.sendKeys(password);
		}
		
		private void ClickTermsAndConditionCheckbox() {
			ckbTermsCondition.click();
		}
		private void HomePageClickSubmitButton() {
			btnSubmit.click();
		}
		
		public LivongoSuppliesPage GetObjectOfSuppiesPage(WebDriver dr) {
			
			LivongoSuppliesPage sp = new LivongoSuppliesPage(driver);
			return sp;
		}
		
}
