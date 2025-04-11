package com.vtiger.crm.genericBaseClass_Test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.vtiger.crm.genericDatabaseUtility.DatabaseUtility;
import com.vtiger.crm.genericFileUtility.PropertiesUtility;
import com.vtiger.crm.genericWebDriverUtility.WebDriverUtility;

import pomClasses.HomePage;
import pomClasses.LoginPage;

public class BaseClass_Test {

	
	public WebDriver driver;
	WebDriverUtility sUtil = new WebDriverUtility();
	DatabaseUtility dbUtil = new DatabaseUtility();
	PropertiesUtility pUtil = new PropertiesUtility();
	
	// for making webdriver reference variable as thread safe
	public static ThreadLocal<WebDriver> driverRef = new ThreadLocal<WebDriver>();
	
	// Database Connection program
	@BeforeSuite
	public void connectToDatabase() throws Exception
	{
		String dbUrl = pUtil.getDataFromPropertiesFile("dburl");
		String dbUn = pUtil.getDataFromPropertiesFile("dbun");
		String dbPwd = pUtil.getDataFromPropertiesFile("dbpwd");
		dbUtil.getDatabaseConnection(dbUrl, dbUn, dbPwd);	
	}
	// Browser launching program
	@Parameters("browser")
	@BeforeClass
	public void launchBrowser(@Optional("firefox")String browser) throws Exception 
	{
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else driver = new FirefoxDriver();
		
		setDriver(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String appUrl = pUtil.getDataFromPropertiesFile("url");
		driver.get(appUrl);
	}
	
	// Login to Application  program
	@BeforeMethod
	public void login() throws IOException
	{
		LoginPage loginPage = new LoginPage(driver);
		String UN = pUtil.getDataFromPropertiesFile("username");
		String PWD = pUtil.getDataFromPropertiesFile("password");
		loginPage.loginToApplication(UN, PWD);
	}
	
	// Logout from application program
	@AfterMethod
	public void logoutFromApplication()
	{
		HomePage homePage = new HomePage(driver);
		homePage.logoutFromApplication(driver);
	}
	
	// Tear down Browser program
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}
	
	
	// Close the Database Connection Program
	@AfterSuite
	public void closeDatabaseConnection()
	{
		dbUtil.closeDBConnection();
	}
	public static void setDriver(WebDriver driver)
	{ 
		driverRef.set(driver);
	} 
	public static WebDriver getDriver()
	{
		return driverRef.get();
	}
}
