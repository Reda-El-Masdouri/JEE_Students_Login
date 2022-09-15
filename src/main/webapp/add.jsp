<!-- verify the existence of the session -->
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
<title>Add Student</title>
</head>
<body>
	<div align="right"> Hi <%=login %><br>
	<a href="Logout"> Logout</a>
	</div>
	<form method="POST" action="Add">
	<table border="1">
		<tr>
			<td>Name</td>
			<td><input type="text" name="name" required></td>
		</tr>
		<tr>
			<td>Inscription date</td>
			<td><input type="date" name="date" required></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td><input type="radio" name="gender" value="female" checked>Female
				<input type="radio" name="gender" value="male">Male
				</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Add">
			<input type="reset" value="Reset"></td>
		</tr>
	</table>
	</form>
</body>
</html>