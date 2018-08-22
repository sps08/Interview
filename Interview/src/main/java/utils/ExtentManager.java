package utils;
import java.io.File;
import java.net.InetAddress;

import org.openqa.selenium.Platform;

import com.relevantcodes.extentreports.ExtentReports;



public class ExtentManager {

	public static ExtentReports extent;
	public static ExtentReports getInstance() {
		if(extent==null) {
			String reportpath = System.getProperty("user.dir")+FrameworkConfig.getPropertyMap().get("ReportPath");
			System.out.println(reportpath);
			extent = new ExtentReports(reportpath);
			try {
				extent.addSystemInfo("Host Name",InetAddress.getLocalHost().getHostName());
				extent.addSystemInfo("User Name", System.getProperty("user.name"));
			}catch( Exception e){
				e.printStackTrace();
				
			}
			
			extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		}
		return extent;
		
	}

}

