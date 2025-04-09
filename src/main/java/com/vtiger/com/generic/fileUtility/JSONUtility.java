package com.vtiger.com.generic.fileUtility;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import genericUtility.IPathUtility;

public class JSONUtility {
	
	public String getDataFromJsonFile(String key) throws Exception
	{
		String content = new String(Files.readAllBytes(Paths.get(IPathUtility.jsonFilePath)));
		JSONObject jsonObj = new JSONObject(content);
		return jsonObj.getString(key);
	}
	
}
