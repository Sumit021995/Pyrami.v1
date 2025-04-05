package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection con;
	public void connectToDatabase(String dbUrl, String UN, String PWD) throws Exception
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		con = DriverManager.getConnection(dbUrl, UN, PWD);
	}
	public String getDataFromTable(int columnNo) throws Exception {
		PropertiesUtility pUtil = new PropertiesUtility();
	    if (con == null || con.isClosed()) {
	        System.out.println("⚠️ Connection lost. Re-establishing connection...");
	        String dbURL = pUtil.getDataFromPropertiesFile("dburl");
	        String dbUN = pUtil.getDataFromPropertiesFile("dbun");
	        String dbPWD = pUtil.getDataFromPropertiesFile("dbpwd");
	        connectToDatabase(dbURL, dbUN, dbPWD);  // Re-establish connection
	    }

	    Statement stmt = con.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM project");
	    
	    while (resultSet.next()) {
	        return resultSet.getString(columnNo); 
	    }
	    return null;
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
	public static void main(String[] args) throws Exception {
		
		PropertiesUtility pUtil = new PropertiesUtility();
		String dbURL = pUtil.getDataFromPropertiesFile("dburl");
		String dbUN = pUtil.getDataFromPropertiesFile("dbun");
		String dbPWD = pUtil.getDataFromPropertiesFile("dbpwd");
		DatabaseUtility dbUtil = new DatabaseUtility();
		dbUtil.connectToDatabase(dbURL, dbUN, dbPWD);
		String data = dbUtil.getDataFromTable(2);
		System.out.println(data);
		
	}
}
