package com.vtiger.com.generic.fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.IPathUtility;

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
	
}
