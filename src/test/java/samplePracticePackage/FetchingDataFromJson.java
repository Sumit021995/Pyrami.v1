package samplePracticePackage;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.vtiger.crm.genericIPathUtility.IPathUtility;



public class FetchingDataFromJson {
	public static void main(String[] args) throws Exception {
		FetchingDataFromJson data = new FetchingDataFromJson();
		// BY jsonReader
		System.out.println(data.jsonReader("browser"));
		System.out.println(data.jsonReader("url"));
		System.out.println(data.jsonReader("username"));
		System.out.println(data.jsonReader("password"));
		System.out.println("***************");
		
	}
	public String jsonReader(String key) throws Exception
	{
		String content = new String(Files.readAllBytes(Paths.get(IPathUtility.jsonFilePath)));
		JSONObject jsonObj = new JSONObject(content);
		return jsonObj.getString(key);
	}
	
}
