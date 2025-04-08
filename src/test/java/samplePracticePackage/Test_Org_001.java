package samplePracticePackage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test_Org_001 {
	public static void main(String[] args) throws Exception{
		
		WebDriver driver=null;
		
		//================== Fetching Data from properties File ========================//
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\TestData\\vtigerCommonData.properties");
		Properties prop = new Properties();
		prop.load(file);
		String url = prop.getProperty("url");
		String browser = prop.getProperty("browser");
		String UN = prop.getProperty("username");
		String PWD = prop.getProperty("password");
		
		//================== Browser invocation ========================//
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equalsIgnoreCase("edge"))
			driver = new EdgeDriver();
		else 
			driver = new FirefoxDriver();
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//================== Launching application ========================//
		driver.get(url);
		
		//================== Launching application ========================//
		
	}
}
