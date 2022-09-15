package web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login")!=null){
			String name = request.getParameter("name");
			String inscription_date = request.getParameter("date");
			String gender = request.getParameter("gender");
			
			// parameter of data base
			String url = "jdbc:mysql://localhost:3306/jee_etudiant";
			String user = "root";
			String pws = "root";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url,user,pws);
				PreparedStatement ps = connection.prepareStatement("INSERT INTO etudiant(name,inscription_date,gender) VALUES(?,?,?)");
				ps.setString(1, name);
				ps.setString(2, inscription_date);
				ps.setString(3, gender);
				ps.executeUpdate();
				
				response.sendRedirect("index.jsp");
				
				ps.close();
				connection.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("Authentification.jsp");
		}
	}

}
