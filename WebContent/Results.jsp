<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%
 	
		String connectionUrl = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;databaseName=ontology;";
	    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +"databaseName=ontology;";
		String userName="sa";
		String passWord="123456";
%>
<body>
<pre>Select The Company Name : <select name="CompanyName" id="CompanyName">
<%
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(connectionUrl,"sa","123456");
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select distinct CompanyName  from autoontology  order by CompanyName");
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
	</select> <span id="results"></span> &nbsp;&nbsp;<input type="button" id="bu" value="Load Model Names" onclick="callJavaScript()"/>
	
	
</body>
</html>
<script type="text/javascript">
	var xhr=null;
	function callJavaScript()
	{
		
		var whichCompany=document.getElementById("CompanyName").value;
		var url="http://localhost:8080/ontoK/getModel.jsp?CompanyName="+whichCompany;
		if(XMLHttpRequest)
		{
		xhr=new XMLHttpRequest();
			if(xhr)
			{
				xhr.open("GET", url, true);
				xhr.onreadystatechange=gimmeData;
				xhr.send(null);
				document.getElementById("bu").style.visibility="hidden";
			}
			else
				{
				alert("Not able to Create XHR");
				}
		}
	else
		{
		alert("Some Problem");
		}
	
		
	}
	function gimmeData()
	{
		if(xhr.readyState==4)
			{
				if(xhr.status==200)
					{
					document.getElementById("results").innerHTML=xhr.responseText;
					}
			}
	}
	function getSentiments()
	{
		var whichCompany=document.getElementById("CompanyName").value;
		var whichModel=document.getElementById("ModelName").value;
		var url="http://localhost:8080/ontoK/getSentiments.jsp?CompanyName="+whichCompany+"&ModelName="+whichModel;
		if(XMLHttpRequest)
		{
		xhr=new XMLHttpRequest();
			if(xhr)
			{
				xhr.open("GET", url, true);
				xhr.onreadystatechange=gimmeDataAgain;
				xhr.send(null);
				
			}
			else
				{
				alert("Not able to Create XHR");
				}
		}
	else
		{
		alert("Some Problem");
		}

	}
	function gimmeDataAgain()
	{
		alert(xhr.readyState);
		if(xhr.readyState==4)
			{
				if(xhr.status==200)
					{
					document.getElementById("results").innerHTML=xhr.responseText;
					}
			}
	}
</script>