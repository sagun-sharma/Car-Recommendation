<%@page import="com.ml.onkmean.Euclidean"%>

<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%!
		
	    String _whichCompany;
		String _whichModel;
		String _findWhat;
		private static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
		private  static String url = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +
				"databaseName=ontology";
		String connectionUrl = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +
			"databaseName=ontology"	;
		private static String userName="sa";
		private static String passWord="123456";
		private static Connection con;
		int _totalPos=0;
		int _totalNeg=0;
		int _totalNeu=0;
		int _sno=0;
		
		double _price=0.00d;
%>
<%
_whichCompany=request.getParameter("CompanyName");
_whichModel=request.getParameter("ModelName");

%>
 
<table border="1" bordercolor="red" width="100%">
<tr bgcolor="#FCF3CF">
	<th>S No</th>
	<th>Company Name</th>
	<th>Version</th>
	<th>Price</th>
	<th>Model Name</th>
	<th>Capacity</th>
	<th>Horse Power</th>
	<th>Torque</th>
	<th>Gear Box</th>
	<th>Top Speed</th>
	<th>Fuel Efficiency in Lts.</th>
	<th>Milage in KMPL</th>
	<th>Petrol</th>
	<th>Diesel</th>
</tr>	

<%
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionUrl,"sa","123456");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select *  from autoontology where CompanyName='" + _whichCompany.trim() +"' and ModelName='" + _whichModel + "'");
			int _seqq=1;
			while(rs.next())
			{
				
%>
		 
			<tr bgcolor="#F0F3F4">
				<%_sno=rs.getInt(1);%>
				<th><%=_seqq++%></th>
				<th><%=rs.getString(2)%></th>
				<th><%=rs.getString(3)%></th>
				<%_price=rs.getDouble(4);%>
				<th><%=_price%></th>
				<th><%=rs.getString(5)%></th>
				<th><%=rs.getString(6)%></th>
				<th><%=rs.getString(7)%></th>
				<th><%=rs.getString(8)%></th>
				<th><%=rs.getString(9)%></th>
				<th><%=rs.getString(10)%></th>
				<th><%=rs.getString(11)%></th>
				<th><%=rs.getString(12)%></th>
				<th><%=rs.getString(13)%></th>
				<th><%=rs.getString(14)%></th>
			</tr>
			
<%				
			}
		}				
			
		catch(Exception e)
		{
			out.print("Error = " + e + "<HR>");
		}
		
	%>
	
</table>

<!--  Some Changes -->
	<table border="1">
	<tr>
		<th>User ID</th>
		<th>User Name</th>
		<th>User Tweets</th>
		<th>Recommendation Analysis</th>
		<th>Recommendation Analysis Over All </th>
<%
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionUrl,"sa","123456");
			Statement stmt = con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select UserId,UserName,Tweets  from Tweettable " + 
			" where Tweets Like '%" + _whichCompany.trim() +" "  + _whichModel + "%' and Tweets is not null");
			
			
			
			while(rs.next())
			{
%>
		<tr bgcolor="#F0F3F4">
				<th><%=rs.getString(1)%></th>
				<th><%=rs.getString(2)%></th>
				<%_findWhat=rs.getString(3);%>
				<th>			<%=_findWhat%></th>
			<%
			Connection con1 = DriverManager.getConnection(connectionUrl,"sa","123456");
			Statement stmt1 = con1.createStatement();
			
				ResultSet rs1=stmt1.executeQuery("select word,Polarity from dictionary");
				int _pos=0;
				int _neg=0;
				int _neu=0;
				
				while(rs1.next())
				{
					String _word=rs1.getString(1);
					System.out.println(_word);
					String charset= "US-ASCII";
					System.out.println(_findWhat);
			        byte[] searchedFor = _word.getBytes(charset);
			        byte[] input = _findWhat.getBytes(charset);
			        int idx=Euclidean.seaEuclidean(input,searchedFor);
			        System.out.println(idx);
			        if(idx > 0)
			        {
			        	
			        	String _beh=rs1.getString(2);
			        	System.out.println(_beh);
			        	if(_beh.equalsIgnoreCase("positive"))
			        	{
							_pos++;
			        	}
			        	if(_beh.equalsIgnoreCase("negative"))
			        	{
							_neg++;
			        	}
			        	if(_beh.equalsIgnoreCase("neutral"))
			        	{
							_neu++;
			        	}
						
						
			        	
			        }
					
				}
				out.println("<th>Pos :" + _pos + " Neg :" + _neg + " Neu :" + _neu +"</th><th>");
				con1.close();
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
					out.println("Positive Recommendation");
					_totalPos++;
				}
				else if(_gn>=1)
				{
					out.println("Negative Recommendation");
					_totalNeg++;
				}
				else if(_gne>=1)
				{
					out.println("Can not Say");
					_totalNeu++;
				}
				
				out.println("</th></tr>");
				con1.close();
					
%>		
		
			</tr>
<%				
			}
		}				
			
		catch(Exception e)
		{
			out.print("Error = " + e + "<HR>");
		}
		
	%>
			
	</table>
	<hr/>
	<pre>
	Total Users Recommended : - <%=_totalPos%>
	Total Users Not Recommended : - <%=_totalNeg%>
	Total Users Can not Answer : - <%=_totalNeu%>
	<%
	_totalPos=0;
	_totalNeg=0;
	_totalNeu=0;
	%>
	</pre>

<hr/>
<table border="1" bordercolor="red" width="100%">
<tr bgcolor="#FCF3CF">
	<th>Company Name</th>
	<th>Version</th>
	<th>Price</th>
	<th>Model Name</th>
	<th>Capacity</th>
	<th>Horse Power</th>
	<th>Torque</th>
	<th>Gear Box</th>
	<th>Top Speed</th>
	<th>Fuel Efficiency in Lts.</th>
	<th>Milage in KMPL</th>
	<th>Petrol</th>
	<th>Diesel</th>
</tr>	

<%
		double _newPrice=_price + 100000;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionUrl,"sa","123456");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select *  from autoontology where price >=" + _price + " and price <= " + _newPrice + " and sno !=" + _sno + "");
			while(rs.next())
			{
				
%>
		 
			<tr bgcolor="#F0F3F4">
				<% %>
				<th><%=rs.getString(2)%></th>
				<th><%=rs.getString(3)%></th>
				<%_price=rs.getDouble(4);%>
				<th><%=_price%></th>
				<th><%=rs.getString(5)%></th>
				<th><%=rs.getString(6)%></th>
				<th><%=rs.getString(7)%></th>
				<th><%=rs.getString(8)%></th>
				<th><%=rs.getString(9)%></th>
				<th><%=rs.getString(10)%></th>
				<th><%=rs.getString(11)%></th>
				<th><%=rs.getString(12)%></th>
				<th><%=rs.getString(13)%></th>
				<th><%=rs.getString(14)%></th>
			</tr>
			
<%				
			}
		}				
			
		catch(Exception e)
		{
			out.print("Error = " + e + "<HR>");
		}
		
	%>
	
</table>
	<hr/>
