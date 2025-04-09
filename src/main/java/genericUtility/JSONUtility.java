package genericUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class JSONUtility {

	public String getDataFromJsonFile(String key) throws IOException
	{
		String jsonString=new String(Files.readAllBytes(Paths.get(IPathUtility.jsonFilePath)));
		JSONObject jObj = new JSONObject(jsonString);
		String value = jObj.getString(key);
		
		return value;
	}
}
