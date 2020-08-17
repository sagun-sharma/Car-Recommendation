package com.ml.onkmean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
public class CleanTweets {
	private static String driverName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private  static String url = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +
			"databaseName=ontology";
	private static String userName="sa";
	private static String passWord="123456";
	private static Connection con;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		
		Class.forName(driverName);
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select Tweets,UserName,Sno from TweetTable where AfterStopWord is Null");
		String ans="";	
		while(rs.next())
		{
			
			String _string=rs.getString(1).trim();
			String _sno=rs.getString(3).trim();
			try{
			ans=Stopwords.replaceStopWords(_string).trim();
			}
			catch(Exception ee)
			{
				
				System.out.println(ee.toString());
			}
			
			try
			{
			Connection con1 = DriverManager.getConnection(url,userName,passWord);
			PreparedStatement stmt1 = con1.prepareStatement("update TweetTable set AfterStopWord=? where sno=?");
			stmt1.setString(1,ans);
			stmt1.setString(2, _sno);
			stmt1.executeUpdate();
			
			System.out.println("\n-----------------------------------------\n");
			
			con1.close();
			}
			catch(Exception ee){}
		}
		
				
		
		con.close();
		
		}
		catch(Exception ee)
		{
			
			System.out.println(ee.getMessage());
			
		}
	}
	}


