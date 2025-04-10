package samplePracticePackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import com.vtiger.crm.genericIPathUtility.IPathUtility;


public class JsonReader {
	public static void main(String[] args) throws IOException {
		String data = new String(Files.readAllBytes(Paths.get(IPathUtility.jsonFilePath)));
		JSONObject jObj = new JSONObject(data);
		String browser = jObj.getString("browser");
		System.out.println(browser);
	}
	
}
