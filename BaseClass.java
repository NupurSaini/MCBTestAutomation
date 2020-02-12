package com.MCBAutomation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	public BaseClass() {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					".\\src\\test\\java\\com\\MCBAutomation\\config\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.getMessage();
		}
		
	}

	public static void initialization() {
		String browserName = prop.getProperty("browserName");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".//ImportantFiles//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					".//ImportantFiles//geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(BaseUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}
	
	public static String getReportConfigPath() {
		String reportConfigPath = prop.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
}
