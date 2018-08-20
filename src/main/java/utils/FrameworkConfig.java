package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class FrameworkConfig {
	
	private static Properties prop;
	static Map<String,String> propMap;
	static {
		prop = new Properties();
		propMap = new HashMap<String,String>();
		try {
			LoadHashMapPropertyValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		private static void LoadHashMapPropertyValues() throws IOException {
			// TODO Auto-generated method stub
			InputStream fileStream = null;
			try {
				fileStream = FrameworkConfig.class.getClassLoader().getResourceAsStream("framework-config.properties");
				prop.load(fileStream);
			} catch(FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			for(final Map.Entry<Object, Object> entry: prop.entrySet()) {
				propMap.put((String) entry.getKey(), (String)entry.getValue());
			}
			
			reloadMapwithCommandLineArguments(propMap);
	}

		private static void reloadMapwithCommandLineArguments(Map<String, String> propMap2) {
			// TODO Auto-generated method stub
			
			String driverFromCL = System.getProperty("browser");
			
			if(driverFromCL!=null && !driverFromCL.trim().isEmpty()) {
				prop.put("browser", driverFromCL);
			}
		}
		public static Map<String,String> getPropertyMap(){
			return propMap;			
		}

}
