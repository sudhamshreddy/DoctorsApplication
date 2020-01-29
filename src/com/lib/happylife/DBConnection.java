package com.lib.happylife;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DBConnection {
	private Connection con;
	public void closeConnection()
	{
		if(con==null)
		{
			return;
		}
		try
		{
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public Connection getConnection()
	{
		Properties pro = new Properties();
		FileInputStream f = null;
		try
		{
			f = new FileInputStream("jdbc.properties");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		try
		{
			pro.load(f);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		 
		 
		String url = pro.getProperty("path");
		String name = pro.getProperty("username");
		String pass = pro.getProperty("password");
		try
		{
			String driver = pro.getProperty("classname");
			Class.forName(driver);
			con = DriverManager.getConnection(url,name, pass);
			System.out.println("connection established");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		 
		return con;
		
	}

	public static void main(String[] args) throws SQLException {
	/*	// TODO Auto-generated method stub
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();
		Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("show tables;");
			while(rs.next()) {
			 	System.out.println(rs.getString(1));
			}*/
		}
	 
		 
		 
	

}
