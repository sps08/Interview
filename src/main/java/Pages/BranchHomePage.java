package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ObjectRepositories.LocatorsHelper;
import utils.GenericHelpers;

public class BranchHomePage {
public WebDriver driver;
GenericHelpers gh;

	
	@FindBy(how= How.XPATH , using = LocatorsHelper.ExploringAllFooterLinks)
	private List<WebElement> AllFooterCompanyLinks;
	
	@FindBy(how= How.XPATH , using = LocatorsHelper.companyLink)
	private WebElement clickCompanyLink;
	
	public BranchHomePage(WebDriver dr){
		this.driver=dr;
		gh = new GenericHelpers(driver);
		PageFactory.initElements(driver,this);
	}
	
	public void ClickOnFooterCompanyTEAMLink() {
		gh.scrollDownVertically();
		AllFooterCompanyLinks.get(1).click();
	}
	
	public TeamPage getTeamPageObject() {
		
		return null;
	}
	public void ClickOnFooterCompanyCONTACTLink() {
		gh.scrollDownVertically();
		AllFooterCompanyLinks.get(4).click();
	}
}
