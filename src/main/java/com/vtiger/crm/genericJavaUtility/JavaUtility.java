package com.vtiger.crm.genericJavaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * This is utility class which contains java related generic methods
 */
public class JavaUtility {
	/**
	 * This is a generic method to generate random number
	 * @param bound
	 * @return
	 */
	public  int  generateRandomNumber(int bound)
	{
		Random r = new Random();
		int num = r.nextInt(bound);
		return num;
	}
	/**
	 * This is a generic method to generate Calendar details 
	 * @param format
	 * @return
	 */
	public String  getCalanderDetails(String format)
	{
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String data = sdf.format(d);
		return data;
		
	}
	/**
	 * 
	 * @param format
	 * @param extraDay
	 * @return
	 */
	public String  getCalanderDetails(String format,int extraDay)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, extraDay);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String data = sdf.format(d);
		return data;
		
	}
	/**
	 * 
	 * @param millisecond
	 * @throws InterruptedException
	 */
	public void waitFromThread(int millisecond) throws InterruptedException
	{
		Thread.sleep(millisecond);
	}
	/**
	 * 
	 * @param stringLength
	 * @return
	 */
	public String getRandomString(int stringLength)
	{
		String alphabet="ASDFGHJKLOPIUYTREWQZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		StringBuilder sb= new StringBuilder(stringLength);
		for(int i=0;i<stringLength;i++)
		{
			int randomNumber = new Random().nextInt(alphabet.length());
			sb.append(alphabet.charAt(randomNumber));
		}
		return sb.toString();
		
	}
	/**
	 * 
	 * @param stringLength
	 * @return
	 */
	public String getRandomAlfaNumericString(int stringLength)
	{
		StringBuilder sb = new StringBuilder(stringLength);
//		String randomString="";
		String alfabets = "QWERTYUIOPASDFGHJKLZ0123456789XCVBNMqwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<stringLength;i++)
		{
			int randomNumber = new Random().nextInt(alfabets.length());
			sb.append(alfabets.charAt(randomNumber));
		}
		return sb.toString();
	}
}
