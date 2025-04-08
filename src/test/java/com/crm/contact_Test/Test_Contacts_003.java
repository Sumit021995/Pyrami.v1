package com.crm.contact_Test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

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

import genericUtility.IPathUtility;

public class Test_Contacts_003 {
	@Test
	public  void contacts_003() throws Exception{
		
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
		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//================== Fetch Data from Excel File ========================//
		FileInputStream excelFile = new FileInputStream(".\\src\\test\\resources\\TestData\\TestExcel.xlsx");
		Workbook wb = WorkbookFactory.create(excelFile);
		String contactFirstName3 = wb.getSheet("Contacts").getRow(8).getCell(1).toString();
		String contactLastName3 = wb.getSheet("Contacts").getRow(8).getCell(2).toString();
		String mobile3 = (long)wb.getSheet("Contacts").getRow(8).getCell(3).getNumericCellValue()+"";
		String email3 = "a"+(int)Math.floor(Math.random()*1000)+wb.getSheet("Contacts").getRow(8).getCell(4).toString();
		String leadSourceDropdownValue3 = wb.getSheet("Contacts").getRow(8).getCell(5).toString();
		
		WebElement titleDropdown = driver.findElement(By.name("salutationtype"));
		s=new Select(titleDropdown);
		s.selectByIndex(1);
		
		driver.findElement(By.name("firstname")).sendKeys(contactFirstName3,Keys.TAB,Keys.TAB,contactLastName3);
		driver.findElement(By.id("mobile")).sendKeys(mobile3);
		driver.findElement(By.id("email")).sendKeys(email3);
		
		
		
//		//================== Use of Select class for Dropdown handling  ========================//
		WebElement leadSourceDropdownEle = driver.findElement(By.name("leadsource"));
		s = new Select(leadSourceDropdownEle);
		s.selectByValue(leadSourceDropdownValue3);
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
		String comfirmationText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String contactFirstNamecomfirmationText = driver.findElement(By.id("dtlview_First Name")).getText();
		String contactLastNamecomfirmationText = driver.findElement(By.id("dtlview_Last Name")).getText();
		String titleDropdownConfirmationTest = driver.findElement(By.id("mouseArea_First Name")).getText();
		String emailConfirmationTest = driver.findElement(By.id("dtlview_Email")).getText();
		String leadSourceConfirmationTest = driver.findElement(By.id("dtlview_Lead Source")).getText();
//		String orgConfirmationText = driver.findElement(By.xpath("//a[text()='"+org2+"']")).getText();
//		String leadSourceDropdownConfirmationTest = driver.findElement(By.id("mouseArea_First Name")).getText();
//		String industryConfirmationText = driver.findElement(By.id("dtlview_Industry")).getText();
//		String typeConfirmationText = driver.findElement(By.id("dtlview_Type")).getText();
//		String ratingConfirmationText = driver.findElement(By.id("dtlview_Rating")).getText();
		Assert.assertEquals(comfirmationText.contains(contactFirstName3), true);
		Assert.assertEquals(comfirmationText.contains(contactLastName3), true);
		Assert.assertTrue(contactFirstNamecomfirmationText.equals(contactFirstName3));
		Assert.assertTrue(contactLastNamecomfirmationText.equals(contactLastName3));
		Assert.assertTrue(titleDropdownConfirmationTest.contains("Mr."));
		Assert.assertTrue(emailConfirmationTest.equals(email3));
		Assert.assertTrue(leadSourceConfirmationTest.equals(leadSourceDropdownValue3));
//		Assert.assertTrue(orgConfirmationText.equals(org2));
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





