package com.vtiger.crm.genericDatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import com.vtiger.crm.genericFileUtility.PropertiesUtility;

public class DatabaseUtility {
	
//	PreparedStatement ps;
	Connection con;
	public void getDatabaseConnection(String dbUrl, String UN, String PWD) throws Exception
	{
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection(dbUrl, UN, PWD);
			System.out.println("✅ Database connection established Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getDatabaseConnection() throws Exception
	{
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
			System.out.println("✅ Database connection established Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> getDataFromTable(int columnNo) throws Exception {
		ResultSet resultSet;
		List<String> resultList = new ArrayList<String>();
		try {
			
			PropertiesUtility pUtil = new PropertiesUtility();
			if (con == null || con.isClosed()) {
				System.out.println("⚠️ Connection lost. Re-establishing connection...");
				String dbURL = pUtil.getDataFromPropertiesFile("dburl");
				String dbUN = pUtil.getDataFromPropertiesFile("dbun");
				String dbPWD = pUtil.getDataFromPropertiesFile("dbpwd");
				getDatabaseConnection(dbURL, dbUN, dbPWD);  // Re-establish connection
			}
			
			Statement stmt = con.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM ninza_hrm_table;");
			
			while (resultSet.next()) {
				resultList.add(resultSet.getString(columnNo)); 
			} 
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return resultList;
	}
	public void updateDataIntoNinza_hrm_Table(String browser, String url,String username,String password) throws Exception
	{
		try {
			String query="insert into ninza_hrm_table values ('"+browser+"','"+url+"','"+username+"','"+password+"');";
			System.out.println(query);
			Statement s = con.createStatement();
			
			int rs=s.executeUpdate(query);
			if(rs>0)
			{
				System.out.println("A new row inserted");
			}
			else
			{
				System.out.println("duplicate row already exists");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void closeDBConnection() {
	    try {
	        if (con != null && !con.isClosed()) {
	            con.close();
	            System.out.println("✅ Database connection closed successfully.");
	        }
	    } catch (SQLException e) {
	        System.out.println("❌ Error while closing DB connection: " + e.getMessage());
	    }
	}
	public ResultSet executeSelectQuery(String query)
	{
		ResultSet result=null;
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(query);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public int executeNonSelectQuery(String query)
	{
		
		int result=0;
		try {
			Statement stmt = con.createStatement();
			result = stmt.executeUpdate(query);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
//	public static void main(String[] args) throws Exception {
//		
//		PropertiesUtility pUtil = new PropertiesUtility();
//		String dbURL = pUtil.getDataFromPropertiesFile("dburl");
//		String dbUN = pUtil.getDataFromPropertiesFile("dbun");
//		String dbPWD = pUtil.getDataFromPropertiesFile("dbpwd");
//		DatabaseUtility dbUtil = new DatabaseUtility();
//		dbUtil.connectToDatabase(dbURL, dbUN, dbPWD);
//		List<String> dataFromTable2 = dbUtil.getDataFromTable(1);
//		List<String> dataFromTable3 = dbUtil.getDataFromTable(2);
//		List<String> dataFromTable4 = dbUtil.getDataFromTable(3);
//		List<String> dataFromTable = dbUtil.getDataFromTable(4);
//		System.out.println(dataFromTable);
//		System.out.println(dataFromTable2);
//		System.out.println(dataFromTable3);
//		System.out.println(dataFromTable4);
//		
//		
//		dbUtil.closeDBConnection();
//	}
}
