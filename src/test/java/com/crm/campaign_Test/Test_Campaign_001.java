package com.crm.campaign_Test;

import org.testng.annotations.Test;

import com.vtiger.crm.genericBaseClass_Test.BaseClass_Test;
import com.vtiger.crm.genericJavaUtility.JavaUtility;

import pomClasses.HomePage;

public class Test_Campaign_001 {
	@Test
	public void campaign_001()
	{
		
//		new HomePage(driver).clickOnCampaignLink(driver);
		String fixedDate = new JavaUtility().getFixedDate("yyyy-MM-dd", 2025, 1, 3);
		System.out.println(fixedDate);
		
		
	}
}
