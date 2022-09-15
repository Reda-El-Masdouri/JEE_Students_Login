<!-- import another language -->
<%@ page language="java"%>
<%@ page import="java.sql.*" %>

<!-- test of the session -->
<%
	String login = "";
	if(session.getAttribute("login")!=null){
		login = session.getAttribute("login").toString();
	}else{
		response.sendRedirect("Authentification.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<div align="right"> Hi <%=login %><br>
	<a href="Logout"> Logout</a>
	</div>
	<h1>Students list</h1>
	<table border="1" width="100%">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Inscription date</th>
			<th>Gender</th>
			<th>Option</th>
		</tr>
		<%
		// parameter of data base
				String url = "jdbc:mysql://localhost:3306/jee_etudiant";
				String user = "root";
				String pws = "root";
				int total_number_students = 0;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(url,user,pws);
					PreparedStatement ps = connection.prepareStatement("SELECT * FROM etudiant");
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						%>
							<tr>
								<th><%= rs.getString(1) %></th>
								<th><%= rs.getString(2) %></th>
								<th><%= rs.getString(3) %></th>
								<th><%= rs.getString(4) %></th>
								<th><a href="Delete?id=<%=rs.getString(1)%>">Delete</a></th>
							</tr>
						<%
						total_number_students++;
					}
					rs.close();
					ps.close();
					connection.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
					}
				
		%>
	</table>
	<p>Total number of students: <%= total_number_students %> </p>
	<p><a href="add.jsp">Add student</a></p>
</body>
</html>