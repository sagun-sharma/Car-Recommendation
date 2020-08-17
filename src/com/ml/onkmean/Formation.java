package com.ml.onkmean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Formation {
	private static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private  static String url = "jdbc:odbc:OntoCon";
	private static String userName="sa";
	private static String passWord="123456";
	private static Connection con;
	
	/**
	 * @param args
	 */
	public static void generateXML(String CompanyName,String Version,String Price ,String ModelName ,String Capacity,String HorsePower,String Torque,int GearBox,String TopSpeed, String FuelEfficiencyInLtrs ,String MilageKMPL ,String Petrol,String Diesel,String CNG,String LPG,String Hybrid)
	{
		try{
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(url,userName,passWord);
			Statement stmt = con.createStatement();
			int status = stmt.executeUpdate("insert into AutoOntology " +
	                "values('" + CompanyName + "','" + Version + "', '" + Price + "','" + ModelName + "','" + Capacity + "','" + HorsePower + "','" + Torque + "'," + GearBox + ",'" + TopSpeed + "','" + FuelEfficiencyInLtrs + "','" + MilageKMPL + "','" + Petrol + "','" + Diesel + "','" + CNG + "','" + LPG + "','" + Hybrid + "')");
	            System.out.println("Added Row");
		}
		catch(Exception ee)
		{
			System.out.println("Added Row" + ee.toString());
		}
		finally
		{
			if(con!=null)
			{
				try{
					con.close();
					
				}
				catch(Exception e)
				{}
				
			}
		}
	}
	public static void collate_formation()
	{
		try
		{
		Class.forName(driverName);
		// get connection
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement stmt = con.createStatement();
		int rs=stmt.executeUpdate("Delete AutoOntology");
		}
		catch(Exception ee)
		{
		}
		finally
		{
			if(con!=null)
			{
				try{
					con.close();
					
				}
				catch(Exception e)
				{}
				
			}
		}
	}

	
	
}
