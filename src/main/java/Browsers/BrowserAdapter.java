package Browsers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import utils.FrameworkConfig;

public class BrowserAdapter {

private WebDriver driver;
	
	public WebDriver initializeBrowserAdapter(String browser){
			DesiredCapabilities caps = null;
			ChromeOptions options;
			switch(browser) {
			case "Chrome":
			
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/"+FrameworkConfig.getPropertyMap().get("ChromeDriver"));
				options = new ChromeOptions();
			    options.addArguments("--disable-extensions");
			    options.addArguments("disable-infobars");
			    driver = new ChromeDriver(options);
			break;
				
			case "IE":
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/"+ FrameworkConfig.getPropertyMap().get("IEDriver"));
				caps = DesiredCapabilities.internetExplorer();
				
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				    true);
				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
				caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				driver = new InternetExplorerDriver(caps);
				driver.manage().deleteAllCookies();
				break;
				
			default:
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ FrameworkConfig.getPropertyMap().get("ChromeDriver"));
				options = new ChromeOptions();
			    options.addArguments("--disable-extensions");
			    options.addArguments("disable-infobars");
			    driver = new ChromeDriver(options);
			
		}
		
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
}

