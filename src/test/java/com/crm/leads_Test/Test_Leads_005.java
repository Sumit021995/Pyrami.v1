package com.crm.leads_Test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
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

import com.vtiger.crm.genericIPathUtility.IPathUtility;



public class Test_Leads_005 {
	@Test
	public  void leads_005() throws Exception{
		
		Select s;
		WebDriver driver=null;
		
		//================== Fetching Data from properties File ========================//
		FileInputStream propertiesFile = new FileInputStream(IPathUtility.propertiesFile);
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
		
		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Leads']")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		//================== Fetch Data from Excel File ========================//
		FileInputStream excelFile = new FileInputStream(IPathUtility.excelFile);
		Workbook wb = WorkbookFactory.create(excelFile);
		Row row = wb.getSheet("Leads").getRow(14);
		String firstName5 = row.getCell(1).toString();
		String lastName5= row.getCell(2).toString();
		String company5 = row.getCell(3).toString()+Math.floor(Math.random()*1000);
		String phone5 = (long)row.getCell(4).getNumericCellValue()+"";
		String website5 = row.getCell(5).toString();
		String employee5 = (int)row.getCell(6).getNumericCellValue()+"";
		String country5 = row.getCell(7).toString();
		String city5 = row.getCell(8).toString();
		String state5 = row.getCell(9).toString();
		String leadSource5 = row.getCell(10).toString();
		String industry5 = row.getCell(11).toString();
		String leadStatus5 = row.getCell(12).toString().trim();
		String rating5 = row.getCell(13).toString();
		
		WebElement titleDropdown = driver.findElement(By.name("salutationtype"));
		new Select(titleDropdown).selectByIndex(1);
		driver.findElement(By.name("firstname")).sendKeys(firstName5,Keys.TAB,Keys.TAB,lastName5,Keys.TAB,phone5,Keys.TAB,company5);
		driver.findElement(By.name("website")).sendKeys(website5);
		driver.findElement(By.id("noofemployees")).sendKeys(employee5);
		driver.findElement(By.id("city")).sendKeys(city5,Keys.TAB,country5,Keys.TAB,state5);
		
		
		//================== Use of Select class for Dropdown handling ========================// 
		WebElement industryDropdownEle = driver.findElement(By.name("industry")); 
		s = new Select(industryDropdownEle);
		s.selectByValue(industry5); 
		WebElement leadsourceDropdownEle = driver.findElement(By.name("leadsource")); 
		s = new Select(leadsourceDropdownEle);
		s.selectByValue(leadSource5); 
		WebElement ratingDropdownEle = driver.findElement(By.name("rating")); 
		s = new Select(ratingDropdownEle);
		s.selectByValue(rating5);
		WebElement leadStatusDropdownEle = driver.findElement(By.name("leadstatus")); 
		s = new Select(leadStatusDropdownEle);
		s.selectByValue(leadStatus5);
		  
//		//================== New Window handling ========================// 
//		  String parentWindowId = driver.getWindowHandle();
//		  driver.findElement(By.xpath("//img[@title='Select']")).click(); 
//		  Set<String> allWindowIds = driver.getWindowHandles();
//		  
//		  for(String id:allWindowIds) { 
//			  if(!id.equals(parentWindowId)) {
//				  driver.switchTo().window(id); break; 
//				  } 
//			  }
//		  
//		  driver.findElement(By.id("search_txt")).sendKeys(memberOf);
//		  driver.findElement(By.name("search")).click();
//		  driver.findElement(By.id("1")).click(); driver.switchTo().alert().accept();
//		  
//		  driver.switchTo().window(parentWindowId);
		 
		
		
		driver.findElement(By.name("button")).click();
		
		//================== Validation of above test ========================//
		String comfirmationText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String titleComfirmationText = driver.findElement(By.id("mouseArea_First Name")).getText();
		String firstNameComfirmationText = driver.findElement(By.id("dtlview_First Name")).getText();
		String lastNameComfirmationText = driver.findElement(By.id("dtlview_Last Name")).getText();
		String phoneComfirmationText = driver.findElement(By.id("dtlview_Phone")).getText();
		String companyComfirmationText = driver.findElement(By.id("dtlview_Company")).getText();
		String websiteComfirmationText = driver.findElement(By.id("dtlview_Website")).getText();
		String countryComfirmationText = driver.findElement(By.id("dtlview_Country")).getText(); 
		String cityComfirmationText = driver.findElement(By.id("dtlview_City")).getText();
		String stateComfirmationText = driver.findElement(By.id("dtlview_State")).getText();
		String employeeComfirmationText = driver.findElement(By.id("dtlview_No Of Employees")).getText();
		String leadSourceComfirmationText = driver.findElement(By.id("dtlview_Lead Source")).getText(); 
		String industryComfirmationText = driver.findElement(By.id("dtlview_Industry")).getText(); 
//		String emailComfirmationText = driver.findElement(By.id("dtlview_Email")).getText();
		String leadStatusComfirmationText = driver.findElement(By.id("dtlview_Lead Status")).getText(); 
		String ratingComfirmationText = driver.findElement(By.id("dtlview_Rating")).getText(); 
		 
		
		Assert.assertTrue(comfirmationText.contains(firstName5));
		Assert.assertTrue(comfirmationText.contains(lastName5));
		Assert.assertTrue(firstNameComfirmationText.equals(firstName5));
		Assert.assertTrue(lastNameComfirmationText.equals(lastName5));
		Assert.assertTrue(titleComfirmationText.contains("Mr."));
		Assert.assertTrue(companyComfirmationText.equals(company5));
		Assert.assertTrue(phoneComfirmationText.equals(phone5));
		if(website5.contains("http://") || website5.contains("https://"))
			Assert.assertTrue(websiteComfirmationText.equals(website5));
		else
			Assert.assertTrue(websiteComfirmationText.equals("http://"+website5));
		
		Assert.assertTrue(phoneComfirmationText.equals(phone5));
		Assert.assertTrue(employeeComfirmationText.equals(employee5));
		Assert.assertTrue(cityComfirmationText.equals(city5));
		Assert.assertTrue(countryComfirmationText.equals(country5));
		Assert.assertTrue(stateComfirmationText.equals(state5));
		Assert.assertTrue(leadSourceComfirmationText.equals(leadSource5));
		Assert.assertTrue(industryComfirmationText.equals(industry5));
		Assert.assertTrue(leadStatusComfirmationText.equals(leadStatus5));
		Assert.assertTrue(ratingComfirmationText.equals(rating5));
		System.out.println("All Leads details Validated True");
		
		//================== Logout From The Application with the help of Actions class ========================//
		Actions act = new Actions(driver);
		WebElement accIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(accIcon).click().perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//================== Tear Down ========================//
		driver.quit();
		
	}
}




