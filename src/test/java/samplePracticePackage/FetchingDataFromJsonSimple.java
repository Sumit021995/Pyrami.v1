package samplePracticePackage;


import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.vtiger.crm.genericIPathUtility.IPathUtility;



public class FetchingDataFromJsonSimple {
	public static void main(String[] args) throws Exception {
		FetchingDataFromJsonSimple data = new FetchingDataFromJsonSimple();
		
		// BY jsonParser
		System.out.println(data.jsonParser("browser"));
		System.out.println(data.jsonParser("url"));
		System.out.println(data.jsonParser("username"));
		System.out.println(data.jsonParser("password"));
		
	}
	
	public String jsonParser(String key) throws IOException, ParseException
    {
    	JSONParser jParser = new JSONParser();
    	FileReader file = new FileReader(IPathUtility.jsonFilePath);
    	Object obj = jParser.parse(file);
    	//convet to JSON Object
    	JSONObject jObj = (JSONObject)obj;
    	String value = jObj.get(key).toString();
    	return value;
    	
    }
}

