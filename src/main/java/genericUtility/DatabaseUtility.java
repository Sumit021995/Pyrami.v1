package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	
//	PreparedStatement ps;
	Connection con;
	public void connectToDatabase(String dbUrl, String UN, String PWD) throws Exception
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		con = DriverManager.getConnection(dbUrl, UN, PWD);
	}
	public List<String> getDataFromTable(int columnNo) throws Exception {
		PropertiesUtility pUtil = new PropertiesUtility();
	    if (con == null || con.isClosed()) {
	        System.out.println("⚠️ Connection lost. Re-establishing connection...");
	        String dbURL = pUtil.getDataFromPropertiesFile("dburl");
	        String dbUN = pUtil.getDataFromPropertiesFile("dbun");
	        String dbPWD = pUtil.getDataFromPropertiesFile("dbpwd");
	        connectToDatabase(dbURL, dbUN, dbPWD);  // Re-establish connection
	    }

	    Statement stmt = con.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM ninza_hrm_table;");
	    
	    List<String> resultList = new ArrayList<String>();
	    while (resultSet.next()) {
	         resultList.add(resultSet.getString(columnNo)); 
	    } 
	    return resultList;
	}
	public void updateDataIntoNinza_hrm_Table(String browser, String url,String username,String password) throws Exception
	{
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
