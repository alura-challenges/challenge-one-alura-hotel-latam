package config;

import java.io.FileInputStream;
import java.util.Properties;

public class Setting {
	
	private final Properties properties;
	private static final String CONFIG_PROPERTIES = "config.properties";
	
	public Setting() {
		properties=new Properties();
		loadProperties(CONFIG_PROPERTIES);
	}
	
	private void loadProperties(String filePath) {
		FileInputStream fileInputStream;
		
		try {
			fileInputStream=new FileInputStream(filePath);
			properties.load(fileInputStream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getProperty(String name) {
		return properties.getProperty(name);
	}
}
	
