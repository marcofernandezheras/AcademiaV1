package model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionManager {

	private static Connection con = null;
	private static String driver = "com.mysql.jdbc.Driver";					
	private static String usr = "root";
	private static String pwd = "marco";
	private static String url = "jdbc:mysql://localhost/academia";
	
	public static Connection getConnection(){
		if(con == null){
			try {
				Runtime.getRuntime().addShutdownHook(new CloseConnectionShDwnHook());
				//Driver load
				Class.forName(driver).newInstance();				
				con = DriverManager.getConnection(url, usr, pwd);
			}
			catch (Exception e){
				throw new RuntimeException("Error connecting to DDBB", e);
			}
		}
		return con;
	}
	private SqlConnectionManager() {}
	
	public static void startTransaccion(){
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(){
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(){
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static class CloseConnectionShDwnHook extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				con.close();
			}
			catch( Exception ex )
			{
				ex.printStackTrace();
				throw new RuntimeException("Error closing to DDBB",ex);
			}
		}
	}	
}
