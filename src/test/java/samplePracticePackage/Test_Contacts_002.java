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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_Contacts_002 {
	@Test
	public  void contacts_002() throws Exception{
		
		Select s;
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
		String orgName1 = wb.getSheet("Organizations").getRow(2).getCell(1).toString()+Math.floor(Math.random()*1000);
		String orgWebsite1 = wb.getSheet("Organizations").getRow(2).getCell(2).toString();
		String orgPhone1 = (long)wb.getSheet("Organizations").getRow(2).getCell(3).getNumericCellValue()+"";
				
		driver.findElement(By.name("accountname")).sendKeys(orgName1,Keys.TAB,Keys.TAB,orgWebsite1,Keys.TAB,orgPhone1);
		driver.findElement(By.name("button")).click();
				
		//================== Validation of above test ========================//
		String comfirmationText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertEquals(comfirmationText.contains(orgName1), true);
		System.out.println("Validated True");
		
		//================== Navigation to Organization module ========================//
		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//================== Fetch Data from Excel File ========================//
		String contactFirstName2 = wb.getSheet("Contacts").getRow(5).getCell(1).toString();
		String contactLastName2 = wb.getSheet("Contacts").getRow(5).getCell(2).toString();
		String mobile2 = (long)wb.getSheet("Contacts").getRow(5).getCell(3).getNumericCellValue()+"";
		String email2 = "a"+(int)Math.floor(Math.random()*1000)+wb.getSheet("Contacts").getRow(2).getCell(4).toString();
//		String org2 = wb.getSheet("Contacts").getRow(5).getCell(5).toString();
		
		WebElement titleDropdown = driver.findElement(By.name("salutationtype"));
		s=new Select(titleDropdown);
		s.selectByIndex(1);
		
		driver.findElement(By.name("firstname")).sendKeys(contactFirstName2,Keys.TAB,Keys.TAB,contactLastName2);
		driver.findElement(By.id("mobile")).sendKeys(mobile2);
		driver.findElement(By.id("email")).sendKeys(email2);
		
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
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName1);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName1+"']")).click();
		
		driver.switchTo().window(parentWindowId);
		
		
//		//================== Use of Select class for Dropdown handling  ========================//
//		WebElement industryDropdownEle = driver.findElement(By.name("industry"));
//		s = new Select(industryDropdownEle);
//		s.selectByValue(industry3);
//		WebElement typeDropdownEle = driver.findElement(By.name("accounttype"));
//		s = new Select(typeDropdownEle);
//		s.selectByValue(type3);
//		WebElement ratingDropdownEle = driver.findElement(By.name("rating"));
//		s = new Select(ratingDropdownEle);
//		s.selectByValue(rating3);
//		
//		
		
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		//================== Validation of above test ========================//
		String comfirmationText2 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String contactFirstNamecomfirmationText = driver.findElement(By.id("dtlview_First Name")).getText();
		String contactLastNamecomfirmationText = driver.findElement(By.id("dtlview_Last Name")).getText();
		String titleDropdownConfirmationTest = driver.findElement(By.id("mouseArea_First Name")).getText();
		String emailConfirmationTest = driver.findElement(By.id("dtlview_Email")).getText();
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()='"+orgName1+"']")));
		
		String orgNameConfirmationText = driver.findElement(By.id("mouseArea_Organization Name")).getText().trim();

		System.out.println(orgNameConfirmationText);
//		String leadSourceDropdownConfirmationTest = driver.findElement(By.id("mouseArea_First Name")).getText();
//		String industryConfirmationText = driver.findElement(By.id("dtlview_Industry")).getText();
//		String typeConfirmationText = driver.findElement(By.id("dtlview_Type")).getText();
//		String ratingConfirmationText = driver.findElement(By.id("dtlview_Rating")).getText();
		Assert.assertEquals(comfirmationText2.contains(contactFirstName2), true);
		Assert.assertEquals(comfirmationText2.contains(contactLastName2), true);
		Assert.assertTrue(contactFirstNamecomfirmationText.equals(contactFirstName2));
		Assert.assertTrue(contactLastNamecomfirmationText.equals(contactLastName2));
		Assert.assertTrue(titleDropdownConfirmationTest.contains("Mr."));
		Assert.assertTrue(emailConfirmationTest.equals(email2));
		Assert.assertEquals(orgNameConfirmationText, orgName1);
//		Assert.assertTrue(leadSourceDropdownConfirmationTest.equals());
//		Assert.assertEquals(typeConfirmationText.contains(type3), true);
//		Assert.assertEquals(ratingConfirmationText.contains(rating3), true);
//		Assert.assertTrue(memberOfConfirmationText.contains(memberOf));
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




