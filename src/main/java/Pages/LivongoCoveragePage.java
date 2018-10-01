package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ObjectRepositories.LocatorsRepo;
import utils.GenericHelpers;

public class LivongoCoveragePage {

	public WebDriver driver;
	GenericHelpers gh;
	
	public LivongoCoveragePage(WebDriver dr){
		this.driver=dr;
		gh = new GenericHelpers(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how= How.ID , using = LocatorsRepo.Coverage_PromoCode)
	private WebElement txtPromoCode;
	
	@FindBy(how= How.ID , using = LocatorsRepo.Coverage_InsuranceDropDown)
	private WebElement ddInsProvider;
	
	@FindBy(how= How.XPATH , using = LocatorsRepo.Coverage_InsuranceValues)
	private List<WebElement> ddInsProviderValues;
	
	@FindBy(how= How.XPATH , using = LocatorsRepo.Coverage_PromoCodeHelp)
	private List<WebElement> promoHelpCount;
	
	@FindBy(how= How.ID , using = LocatorsRepo.Coverage_MemberID)
	private WebElement txtMemberID;
	
	@FindBy(how= How.ID , using = LocatorsRepo.Coverage_GroupID)
	private WebElement txtGroupID;
	
	@FindBy(how= How.CLASS_NAME , using = LocatorsRepo.Coverage_HealthButton)
	private WebElement btnSubmit;
	
	public void FillCoverageForm(String code, String InsProvider, String memberID, String groupID) throws InterruptedException {
		EnterPromoCode(code);
		SelectInsuranceProvider(InsProvider);
		EnterMemberIDAndGroupID(memberID,groupID);
		//CoverageClickSubmitButton();

	}
	private void EnterPromoCode(String code) throws InterruptedException {
		txtPromoCode.sendKeys(code);
		int totalRecords = promoHelpCount.size();
			//System.out.println(promoHelpCount.get(0).getText());;
			promoHelpCount.get(0).click();
	 }
	
	
	private void SelectInsuranceProvider(String IP) throws InterruptedException {
		//Thread.sleep(1000);
		ddInsProvider.click();
		//System.out.println(ddInsProviderValues.size()+"   size");
		for(int i=0;i<ddInsProviderValues.size();i++) {
			if(ddInsProviderValues.get(i).getText().equals(IP)) {
				ddInsProviderValues.get(i).click();
			}
		}
	}
	
	private void EnterMemberIDAndGroupID(String memberID, String groupID) {
		txtMemberID.sendKeys(memberID);
		txtGroupID.sendKeys(groupID);
	}
	
	private void CoverageClickSubmitButton() {
		btnSubmit.click();
	}
	
}
