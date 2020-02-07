package DriverScript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import Utilities.PropertyFileReader;

public class Base {

	/*
	 * @author: Prasanta Banerjee.
	 * @date:   7th Feb, 2020.
	 */

	static WebDriver driver;
	static PropertyFileReader config = new PropertyFileReader();
	static String browserName = config.getProperty("Browser");
	static String driverPath = System.getProperty("user.dir") + config.getProperty("driversFolderPath");

	public static WebDriver getDriver() {
		if (driver == null) {
			killAllDriverInstancesBeforeExecution();
			try {
				if (browserName.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				} else if (browserName.equalsIgnoreCase("Firefox")) {
					System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (browserName.equalsIgnoreCase("IE")) {
					System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
				System.out.println("Launched " + browserName + " browser.");
			} catch (Exception e) {
				System.out.println("Exception occured: " + e.getMessage());
			}
			return driver;
		} else {
			return driver;
		}
	}

	public void endDriver() {
		try {
			if (driver != null) {
				driver.quit();
			}
			System.out.println(browserName + " browser sessions ended.");
		} catch (Exception e) {
			System.out.println("Exception occured when closing browser sessions: " + e.getMessage());
		}
	}

	public static void killAllDriverInstancesBeforeExecution() {
		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			} else if (browserName.equalsIgnoreCase("IE")) {
				Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			}
			System.out.println("Killed all instances of " + browserName + " browser.");
		} catch (Exception e) {
			System.out.println("Unable to kill " + browserName + " browser instances. An exception occured: " + e.getMessage());
		}
	}

	public void launchBrowser(String URL) {
		try {
			if (!URL.contains("https://")) {
				URL = "https://" + URL;
			}
			getDriver().get(URL);
		} catch (TimeoutException e) {
			System.out.println("Timeout Exception occured: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unknown exception has occured: " + e.getMessage());
		}
	}

	@BeforeTest
	public void setUp() {
		String baseURL = config.getProperty("URL");
		launchBrowser(baseURL);
	}

	@AfterTest
	public void tearDown() {
		endDriver();
	}
}