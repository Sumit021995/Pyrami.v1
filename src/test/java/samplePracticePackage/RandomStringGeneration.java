package samplePracticePackage;

import java.util.Random;

public class RandomStringGeneration {
	public StringBuilder getRandomString(int stringLength)
	{
//		String randomString="";
		StringBuilder sb = new StringBuilder(stringLength);
		String alfabets = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<stringLength;i++)
		{
			int randomNumber = new Random().nextInt(alfabets.length());
			sb.append(alfabets.charAt(randomNumber));
		}
		return sb;
	}
	public StringBuilder getRandomAlfaNumericString(int stringLength)
	{
		StringBuilder sb = new StringBuilder(stringLength);
//		String randomString="";
		String alfabets = "QWERTYUIOPASDFGHJKLZ0123456789XCVBNMqwertyuiopasdfghjklzxcvbnm";
		for(int i=0;i<stringLength;i++)
		{
			int randomNumber = new Random().nextInt(alfabets.length());
			sb.append(alfabets.charAt(randomNumber));
		}
		return sb;
	}
	public static void main(String[] args) {
		StringBuilder randomString = new RandomStringGeneration().getRandomString(12);
		System.out.println(randomString);
		StringBuilder randomAlfaNumericString = new RandomStringGeneration().getRandomAlfaNumericString(100);
		System.out.println(randomAlfaNumericString);
	}
}
