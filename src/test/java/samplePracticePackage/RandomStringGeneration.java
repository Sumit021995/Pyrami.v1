package samplePracticePackage;

import java.util.Random;

public class RandomStringGeneration {
	public String getRandomString(int stringLength)
	{
		String randomString="";
		String alfabets = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<stringLength;i++)
		{
			int randomNumber = new Random().nextInt(alfabets.length());
			randomString += alfabets.charAt(randomNumber);
		}
		return randomString;
	}
	public String getRandomAlfaNumericString(int stringLength)
	{
		String randomString="";
		String alfabets = "QWERTYUIOPASDFGHJKLZ0123456789XCVBNMqwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<stringLength;i++)
		{
			int randomNumber = new Random().nextInt(alfabets.length());
			randomString += alfabets.charAt(randomNumber);
		}
		return randomString;
	}
	public static void main(String[] args) {
		String randomString = new RandomStringGeneration().getRandomString(12);
		System.out.println(randomString);
		String randomAlfaNumericString = new RandomStringGeneration().getRandomAlfaNumericString(12);
		System.out.println(randomAlfaNumericString);
	}
}
