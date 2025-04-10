package com.vtiger.crm.genericWebDriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

/**
 * This is an Utility class which contains generic methods for selenium library
 * @author-Sumit
 * @version-01.10.25
 */
public class WebDriverUtility {
	
	/**
	 * This is the generic method to open an application
	 * @param driver
	 * @param url
	 */
	public void accesToApplication(WebDriver driver,String url)
	{
		driver.get(url);
	}
	/**
	 * This is the generic method to perform  browser maximization
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This is the generic method to apply implicit wait to the script
	 * @param driver
	 * @param maxTime
	 */
	public void implicitWait(WebDriver driver,int maxTime)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(maxTime));
	}
	
	////////////******Handling Dropdowns*******/////////////
	/**
	 * This is the generic method to select an option from dropdown based on index
	 * @param dropDownElement
	 * @param index
	 */
	public void selectOptionUsingIndex(WebElement dropDownElement, int index)
	{
		Select s = new Select(dropDownElement);
		s.selectByIndex(index);
	}
	/**
	 * This is the generic method to select an option from dropdown based on attribute value
	 * @param dropDownElement
	 * @param attributeValue
	 */
	public void selectOptionUsingValue(WebElement dropDownElement, String attributeValue)
	{
		Select s = new Select(dropDownElement);
		s.selectByValue(attributeValue);
	}
	/**
	 * This is the generic method to select an option from dropdown based on visible text
	 * @param dropDownElement
	 * @param visibleText
	 */
	public void selectOptionUsingVisibleText(WebElement dropDownElement, String visibleText)
	{
		Select s = new Select(dropDownElement);
		s.selectByVisibleText(visibleText);
	}
	
	////////////******Handling Alerts*******/////////////
	
	/**
	 * This is a  generic method to accept the alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
		
	}
	/**
	 * This is a  generic method to dismiss the alert popup
	 * @param driver
	 */
	public void dismisAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
		
	}
	/**
	 * This is a generic method to accept alert with message
	 * @param driver
	 * @param message
	 */
	public void acceptAlertWithMessage(WebDriver driver,String message)
	{
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(message);
		alert.accept();
		
	}
	/**
	 * This is a generic method to dismiss alert with message
	 * @param driver
	 * @param message
	 */
	public void dismissAlertWithMessage(WebDriver driver,String message)
	{
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(message);
		alert.dismiss();
		
	}
	/**
	 * This is a  generic method to get text value of  the alert popup
	 * @param driver
	 * @return String
	 */
	public String getAlertMessage(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
		
	}
	/**
	 * This is a generic method to pass value to alert popup
	 * @param driver
	 * @param vlaue
	 */
	public void passValueToAlert(WebDriver driver,String vlaue)
	{
		 driver.switchTo().alert().sendKeys(vlaue);	
	}
	
	////////////******Handling Mouse Actions*******/////////////
	
	/**
	 * This is a generic method to perform mouse hovering action on a WebElement
	 * @param driver
	 * @param element
	 */
	public void mouseHoveringAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This is a generic method to perform mouse click action on a WebElement
	 * @param driver
	 * @param element
	 */
	public void mouseClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.click(element).perform();
	}
	
	/**
	 * This is a generic method to perform mouse double click action on a WebElement
	 * @param driver
	 * @param element
	 */
	public void mouseDoubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This is a generic method to click on input type webelement and send message to it
	 * @param driver
	 * @param element
	 * @param message
	 */
	public void mouseSendKeysAction(WebDriver driver,WebElement element,String message)
	{
		Actions act = new Actions(driver);
		act.click(element).sendKeys(message).perform();
	}
	
	////////////******Handling Frames*******/////////////
	
	/**
	 * This is a generic method to switch to a frame using index
	 * @param driver
	 * @param index
	 */
	public void switchToFrameUsingIndex(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This is a generic method to switch to a frame using name or id attribute
	 * @param driver
	 * @param is nameOrIdAttribute
	 */
	public void switchToFrameUsingNameOrId(WebDriver driver,String nameOrIdAttribute)
	{
		driver.switchTo().frame(nameOrIdAttribute);
	}
	/**
	 * This is a generic method to switch to a frame using frame WebElement
	 * @param driver
	 * @param is frameElement
	 */
	public void switchToFrameUsingWebElement(WebDriver driver,WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
	
	////////////******Handling Screenshot*******/////////////
	
	/**
	 * This generic method is used to take screenshot of a Web Page and return absolutePath of screenshot location
	 * @param driver
	 * @param dateTimeStamp
	 * @return String
	 * @throws IOException
	 */
	public String getWebPageScreenshot(WebDriver driver,String dateTimeStamp) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		File location= new File(".\\Screenshot\\bugScreenshot-"+dateTimeStamp+".png");
		FileHandler.copy(file, location);
		return location.getAbsolutePath(); // used in Listeners

	}
	/**
	 * This generic method is used to take screenshot of a Web Page as BASE64 format
	 * @param driver
	 * @param dateTimeStamp
	 * @return String
	 * @throws IOException
	 */
	public String getWebPageScreenshotFromBase64(WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		String path = ts.getScreenshotAs(OutputType.BASE64);
		return path; // used in Listeners
		
	}
	////////////******Handling Window*******/////////////
	
	/**
	 * This is a generic method to switch a driver to new generated Window
	 * @param driver
	 */
	public void switchToNewWindow(WebDriver driver)
	{
		String parentId= driver.getWindowHandle();
		Set<String> ids=driver.getWindowHandles();
		for(String s:ids)
		{
			if(!s.equals(parentId))
			{
				driver.switchTo().window(s);
			}
		}
		
		
	}
	
}
