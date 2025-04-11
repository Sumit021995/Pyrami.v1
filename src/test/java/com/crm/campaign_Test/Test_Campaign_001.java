package com.crm.campaign_Test;

import org.testng.annotations.Test;

import com.vtiger.crm.genericBaseClass_Test.BaseClass_Test;

import pomClasses.HomePage;

public class Test_Campaign_001 extends BaseClass_Test{
	@Test
	public void campaign_001()
	{
		
		HomePage homePage = new HomePage(driver);
	}
}
