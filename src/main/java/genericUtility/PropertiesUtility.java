package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PropertiesUtility {
	/**
	 * This is a generic method to get String type data from properties file based on relevant key.
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDataFromPropertiesFile(String key) throws IOException
	{
		FileInputStream file = new FileInputStream(IPathUtility.propertiesFile);
		Properties prop = new Properties();
		prop.load(file);
		String value = prop.getProperty(key);
		return value;
	}
	public static void main(String[] args) throws IOException {
		WebDriver driver;
		System.out.println("Enter a browser name ");
		String browser = new Scanner(System.in).next();
		
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else
			driver = new ChromeDriver();
		/*
		 * System.out.println(new
		 * PropertiesUtility().getDataFromPropertiesFile("browser"));
		 * System.out.println(new PropertiesUtility().getDataFromPropertiesFile("url"));
		 * System.out.println(new
		 * PropertiesUtility().getDataFromPropertiesFile("username"));
		 * System.out.println(new
		 * PropertiesUtility().getDataFromPropertiesFile("password"));
		 */
		PropertiesUtility pUtil = new PropertiesUtility();
		String url = pUtil.getDataFromPropertiesFile("url");
		String UN = pUtil.getDataFromPropertiesFile("username");
		String PWD = pUtil.getDataFromPropertiesFile("password");
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(UN);
		driver.findElement(By.id("submitButton")).click();
		
		//teardown
		driver.quit();
	}
}
