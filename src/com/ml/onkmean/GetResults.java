package com.ml.onkmean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
public class GetResults {

	private static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
	private  static String url = "jdbc:odbc:OntoCon";
	private static String userName="sa";
	private static String passWord="123456";
	private static Connection con;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
		Class.forName(driverName);
		// get connection
		Connection con = DriverManager.getConnection(url,userName,passWord);
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select Tweets,UserName from TweetTable");
		String ans="";	
		while(rs.next())
		{
			
			String _string=rs.getString(1).trim();
			try{
			ans=Stopwords.replaceStopWords(_string).trim();
			}
			catch(Exception ee)
			{}
			System.out.print(rs.getString(2) + "\t" + ans);
			try
			{
			Connection con1 = DriverManager.getConnection(url,userName,passWord);
			Statement stmt1 = con1.createStatement();	
			ResultSet rs1=stmt1.executeQuery("select word,polarity from dictionary");
			int _pos=0;
			int _neg=0;
			int _neu=0;
			while(rs1.next())
			{
				String _word=rs1.getString(1);
				String charset= "US-ASCII";
		        byte[] searchedFor = _word.getBytes(charset);
		        byte[] input = ans.toString().getBytes(charset);
		        int idx=Stopwords.searchNav(input, searchedFor);
		        if(idx > 0)
		        {
		        	String _beh=rs1.getString(2);
		        	switch (_beh) {
					case "positive":
						_pos++;
						break;
					case "negative":
						_neg++;
						break;
					case "neutral":
						_neu++;
						break;
					default:
						break;
					}
		        	
		        }
				
			}
			System.out.print(" #Pos:" + _pos + " Neg:" + _neg + " Neu:" + _neu +"~~");
			int _gp=0,_gn=0,_gne=0;
			if(_pos > _neg && _pos > _neu)
			{
				_gp++;
			}
			else if(_neg >_pos && _neg > _neu )
			{
				_gn++;
			}
			else
			{
				_gne++;
			}
			if(_gp>=1)
			{
				System.out.print("User Positive");
			}
			else if(_gn>=1)
			{
				System.out.print("User Negative");
			}
			else if(_gne>=1)
			{
				System.out.print("User Neutral");
			}
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
	
