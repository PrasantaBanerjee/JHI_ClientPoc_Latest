package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileReader {

	static Properties property;
	public final String propertyFilePath = System.getProperty("user.dir") + "\\Property Files\\config.properties";

	public PropertyFileReader() {
		try {
			property = new Properties();
			FileInputStream fis = new FileInputStream(propertyFilePath);
			property.load(fis);
		} catch (Exception e) {
			System.out.println("Exception occured when loading the properties file: " + e.getMessage());
		}
	}

	public String getProperty(String key) {
		return property.getProperty(key);
	}
}
