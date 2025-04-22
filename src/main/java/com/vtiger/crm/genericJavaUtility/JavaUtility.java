package com.vtiger.crm.genericJavaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * This is utility class which contains java related generic methods
 */
public class JavaUtility {
	Calendar cal;
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
		cal = Calendar.getInstance();
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
	public String  getCalanderDetailsBasedOnFormat(String format,int extraDay)
	{
		cal = Calendar.getInstance();
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
	public String getFixedDate(String dateFormat,int year,int month,int day)
	{
		Calendar newCal = Calendar.getInstance();
		if(month>=1 && month<=12 && day>=1 && day<=31)
		{
			
		}
		newCal.set(year,month-1, day);
        Date fixedDate = newCal.getTime();

        // Format
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formattedDate = sdf.format(fixedDate);
        return formattedDate;

	}
}
