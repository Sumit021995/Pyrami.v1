package samplePracticePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FetchDataFromCMDUsingMavenTest {

	@Test
	public void getDataFromCmdTest() throws InterruptedException
	
	{
		/*String browser = System.getProperty("browser");
		String url = System.getProperty("url");
		String UN = System.getProperty("username");
		String PWD = System.getProperty("password");
		
		System.out.println(browser);
		System.out.println(url);
		System.out.println(UN);
		System.out.println(PWD);*/
		
		WebDriver driver;
//		System.out.println("Enter a browser name ");
//		String browser = new Scanner(System.in).next();
		String browser = System.getProperty("browser");
		
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
		String url = System.getProperty("url");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
//		PropertiesUtility pUtil = new PropertiesUtility();
//		String url = pUtil.getDataFromPropertiesFile("url");
//		String UN = pUtil.getDataFromPropertiesFile("username");
//		String PWD = pUtil.getDataFromPropertiesFile("password");
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//teardown
		Thread.sleep(3000);
		driver.quit();
	}
}
