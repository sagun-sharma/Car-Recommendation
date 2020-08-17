package com.ml.onkmean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import dictionary.Dictionary;

public class featureExtraction {
	private static String driverName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private  static String url = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +
			"databaseName=ontology;";
	private static String userName="sa";
	private static String passWord="123456";
	private static Connection con;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			String definition = Dictionary.definition("@etenergyworld");
			System.out.println(definition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try
		{
		
		Class.forName(driverName);
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select AfterStopWord,sno from TweetTable where FeatureValues is null");
		String ans="";
		StringBuilder b = null;
		while(rs.next())
		{
			
			String _string=rs.getString(1).trim();
			String _sno=rs.getString(2).trim();
			try{
			String _ans[]=_string.split(" ");
			b=new StringBuilder();
			for (String string : _ans) {
				try {
					String definition = Dictionary.definition(string).trim();
					System.out.println(definition);
					b.append(definition);
					b.append(" ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			}
			catch(Exception ee)
			{
				
				System.out.println(ee.toString());
			}
			
			try
			{
			Connection con1 = DriverManager.getConnection(url,userName,passWord);
			PreparedStatement stmt1 = con1.prepareStatement("update TweetTable set FeatureValues=? where sno=?");
			stmt1.setString(1,b.toString());
			stmt1.setString(2, _sno);
			stmt1.executeUpdate();
			
			System.out.println("\n-----------------------------------------\n");
			
			con1.close();
			}
			catch(Exception ee){
				System.out.println(ee.toString() + "Some Problem");
				
			}
		}
		
				
		
		con.close();
		
		}
		catch(Exception ee)
		{
			
			System.out.println(ee.getMessage() + "Please print Here");
			
		}
		
	}

}
