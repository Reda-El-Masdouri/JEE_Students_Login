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
import java.sql.SQLException;


public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login")!=null){
			String id = request.getParameter("id");
			
			
			// parameter of data base
			String url = "jdbc:mysql://localhost:3306/jee_etudiant";
			String user = "root";
			String pws = "root";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url,user,pws);
				PreparedStatement ps = connection.prepareStatement("DELETE FROM etudiant WHERE id=?");
				ps.setString(1, id);
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
