package samplePracticePackage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Org_003 {
	@Test
	public  void org_003() throws Exception{
		
		WebDriver driver=null;
		
		//================== Fetching Data from properties File ========================//
		FileInputStream propertiesFile = new FileInputStream(".\\src\\test\\resources\\TestData\\vtigerCommonData.properties");
		Properties prop = new Properties();
		prop.load(propertiesFile);
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
		else  driver = new FirefoxDriver();
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		//================== Launching application ========================//
		driver.get(url);
		
		//================== Login to  application ========================//
		driver.findElement(By.name("user_name")).sendKeys(UN,Keys.TAB,PWD,Keys.ENTER);
		
		//================== Navigation to Organization module ========================//
		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//================== Fetch Data from Excel File ========================//
		FileInputStream excelFile = new FileInputStream(".\\src\\test\\resources\\TestData\\TestExcel.xlsx");
		Workbook wb = WorkbookFactory.create(excelFile);
		String orgName3 = wb.getSheet("Organizations").getRow(8).getCell(1).toString()+Math.floor(Math.random()*1000);
		String orgWebsite3 = wb.getSheet("Organizations").getRow(8).getCell(2).toString();
		String orgPhone3 = (long)wb.getSheet("Organizations").getRow(8).getCell(3).getNumericCellValue()+"";
		String industry3 = wb.getSheet("Organizations").getRow(8).getCell(4).toString();
		String type3 = wb.getSheet("Organizations").getRow(8).getCell(5).toString();
		String rating3 = wb.getSheet("Organizations").getRow(8).getCell(6).toString();
		String memberOf = wb.getSheet("Organizations").getRow(8).getCell(7).toString();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName3,Keys.TAB,Keys.TAB,orgWebsite3,Keys.TAB,orgPhone3);
		
		//================== Use of Select class for Dropdown handling  ========================//
		Select s;
		WebElement industryDropdownEle = driver.findElement(By.name("industry"));
		s = new Select(industryDropdownEle);
		s.selectByValue(industry3);
		WebElement typeDropdownEle = driver.findElement(By.name("accounttype"));
		s = new Select(typeDropdownEle);
		s.selectByValue(type3);
		WebElement ratingDropdownEle = driver.findElement(By.name("rating"));
		s = new Select(ratingDropdownEle);
		s.selectByValue(rating3);
		
		//================== New Window handling  ========================//
		String parentWindowId = driver.getWindowHandle();
		driver.findElement(By.xpath("//img[@title='Select']")).click();
		Set<String> allWindowIds = driver.getWindowHandles();
		
		for(String id:allWindowIds)
		{
			if(!id.equals(parentWindowId)) {
				driver.switchTo().window(id);
				break;
			}
		}
		
		driver.findElement(By.id("search_txt")).sendKeys(memberOf);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.id("1")).click();
		driver.switchTo().alert().accept();
		
		driver.switchTo().window(parentWindowId);
		
		driver.findElement(By.name("button")).click();
		
		//================== Validation of above test ========================//
		String comfirmationText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String industryConfirmationText = driver.findElement(By.id("dtlview_Industry")).getText();
		String typeConfirmationText = driver.findElement(By.id("dtlview_Type")).getText();
		String ratingConfirmationText = driver.findElement(By.id("dtlview_Rating")).getText();
		String memberOfConfirmationText = driver.findElement(By.id("mouseArea_Member Of")).getText();
		Assert.assertEquals(comfirmationText.contains(orgName3), true);
		Assert.assertEquals(industryConfirmationText.contains(industry3), true);
		Assert.assertEquals(typeConfirmationText.contains(type3), true);
		Assert.assertEquals(ratingConfirmationText.contains(rating3), true);
		Assert.assertTrue(memberOfConfirmationText.contains(memberOf));
		System.out.println("All Organization details Validated True");
		
		//================== Logout From The Application with the help of Actions class ========================//
		Actions act = new Actions(driver);
		WebElement accIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(accIcon).click().perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//================== Tear Down ========================//
		driver.quit();
		
	}
}


