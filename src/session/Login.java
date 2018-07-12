package session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.Connect;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private ResultSet rs;

	private String cpswd;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String pswd = request.getParameter("pswd");
		Connect db = new Connect();
		db.openConnection();
		if (checkLogin(email, pswd, db) == true) {

			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			session.setAttribute("type", getAccountType(email, db));
			session.setAttribute("fname", getFirstName(email,db));
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("RegisterFailed.html");
		}
	}

	public boolean checkLogin(String email, String pswd, Connect conn) {
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select password from account where email='" + email + "'");
		} catch (Exception e) {
			System.out.println("Cant check login info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					
					cpswd = rs.getString("password");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		if (pswd.equals(cpswd)) {
			return true;
		} else {
			return false;
		}

	}

	public String getAccountType(String email, Connect conn) {
		Statement st;
		String type = null;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select type from account where email='" + email + "'");
		} catch (Exception e) {
			System.out.println("Cant get account info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					type = rs.getString("type");

				}
			}
			
		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		return type;
	}
	public String getFirstName(String email, Connect conn) {
		Statement st;
		String fname = null;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select firstName from account where email='" + email + "'");
		} catch (Exception e) {
			System.out.println("Cant get account info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					fname = rs.getString("firstname");

				}
			}
			
		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		return fname;
	}
}
