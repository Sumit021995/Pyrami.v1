package com.crm.org_Test;

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

public class Test_Org_002 {
	@Test
	public  void org_002() throws Exception{
		
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
		String orgName2 = wb.getSheet("Organizations").getRow(5).getCell(1).toString()+Math.floor(Math.random()*1000);
		String orgWebsite2 = wb.getSheet("Organizations").getRow(5).getCell(2).toString();
		String orgPhone2 = wb.getSheet("Organizations").getRow(5).getCell(3).getNumericCellValue()+"";
		String industry2 = wb.getSheet("Organizations").getRow(5).getCell(4).toString();
		String type2 = wb.getSheet("Organizations").getRow(5).getCell(5).toString();
		String rating2 = wb.getSheet("Organizations").getRow(5).getCell(6).toString();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName2,Keys.TAB,Keys.TAB,orgWebsite2,Keys.TAB,orgPhone2);
		
		//================== Use of Select class for Dropdown handling  ========================//
		Select s;
		WebElement industryDropdownEle = driver.findElement(By.name("industry"));
		s = new Select(industryDropdownEle);
		s.selectByValue(industry2);
		WebElement typeDropdownEle = driver.findElement(By.name("accounttype"));
		s = new Select(typeDropdownEle);
		s.selectByValue(type2);
		WebElement ratingDropdownEle = driver.findElement(By.name("rating"));
		s = new Select(ratingDropdownEle);
		s.selectByValue(rating2);
		driver.findElement(By.name("button")).click();
		
		//================== Validation of above test ========================//
		String comfirmationText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String industryConfirmationText = driver.findElement(By.id("dtlview_Industry")).getText();
		String typeConfirmationText = driver.findElement(By.id("dtlview_Type")).getText();
		String ratingConfirmationText = driver.findElement(By.id("dtlview_Rating")).getText();
		Assert.assertEquals(comfirmationText.contains(orgName2), true);
		Assert.assertEquals(industryConfirmationText.contains(industry2), true);
		Assert.assertEquals(typeConfirmationText.contains(type2), true);
		Assert.assertEquals(ratingConfirmationText.contains(rating2), true);
		System.out.println("Organization details Validated True");
		
		//================== Logout From The Application with the help of Actions class ========================//
		Actions act = new Actions(driver);
		WebElement accIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(accIcon).click().perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//================== Tear Down ========================//
		driver.quit();
		
	}
}

