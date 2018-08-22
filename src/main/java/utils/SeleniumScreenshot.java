package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class SeleniumScreenshot {
	WebDriver driver;
	static String  destination = "";
	static String ssName = "";
	public static void SaveScreenshot(WebDriver driver) {
		File srcFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			File destFile = new File("datetime.png");
			FileUtils.copyFile(srcFile, destFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static String saveSeleniumScreenshots(WebDriver driver, String screenshotName){
		
		DateFormat df= new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String strDate = (String)df.format(new Date());
		strDate.replace("-", "_").replace(":", "_");
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			ssName = screenshotName+strDate+"_.png";
			destination = System.getProperty("user.dir")+FrameworkConfig.getPropertyMap().get("ScreenShotFolder")+ssName;
			System.out.println(destination);
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return destination;
		return System.getProperty("user.dir")+"/Screenshots/"+ssName;
	}



}
