package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import ObjectRepositories.LocatorsHelper;
import utils.FrameworkConfig;

public class GooglePage {
	
public WebDriver driver;
	
	@FindBy(how= How.ID , using = LocatorsHelper.googleSearchBox)
	private WebElement txtGoogleSearch;
	
	@FindBy(how= How.XPATH , using = LocatorsHelper.companyLink)
	private WebElement clickCompanyLink;
	
	public GooglePage(WebDriver dr){
		this.driver=dr;
		PageFactory.initElements(driver,this);
	}
	
	public void getGoogleUrl(){
		driver.get(FrameworkConfig.getPropertyMap().get("GoogleURL"));
	}
	
	public void SearchCompanyNameonGoogle(String searchWord) {
		txtGoogleSearch.sendKeys(searchWord);
		txtGoogleSearch.sendKeys(Keys.ENTER);
	}

	public void ClickCompanyLinkFromGoogleResults() {
		clickCompanyLink.click();
	}
}
