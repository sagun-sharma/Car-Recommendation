<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%!
private static String driverName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
private  static String url = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +
		"databaseName=ontology;";
private static String userName="sa";
private static String passWord="123456";
		String _whichModel;
%>
<%
_whichModel=request.getParameter("CompanyName");
%>
The Model Name : <select name="ModelName" id="ModelName">
<%
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url,"sa","123456");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select distinct ModelName  from autoontology where CompanyName='" + _whichModel.trim() +"'");
			while(rs.next())
			{
%>
			<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
<%				
			}
		}				
			
		catch(Exception e)
		{
			out.print("Error = " + e + "<HR>");
		}
	
	%>
	</select> : <input type="button" value="Get Reccomendation" onclick="getSentiments()"/>