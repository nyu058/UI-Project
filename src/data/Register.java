package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private ResultSet rs;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pswd = request.getParameter("pswd");
		String phone = request.getParameter("phone");

		Connect db = new Connect();
		db.openConnection();

		if (createUser(lname, fname, phone, email, pswd, db)) {
			response.sendRedirect("RegisterSuccess.html");
		} else {
			response.sendRedirect("RegisterFailed.html");
		}
	}

	public boolean createUser(String lname, String fname, String phone, String email, String pswd, Connect conn) {
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			st.executeUpdate("Insert into account values(" + getID(conn) + ", '" + lname + "', '" + fname + "', '"
					+ phone + "', '" + email + "', '" + pswd + "', \'customer\')");

		} catch (Exception e) {
			System.out.println("Cant insert user info");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int getID(Connect conn) {
		int id = 0;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from account  order by  id desc limit 1");
		} catch (Exception e) {
			System.out.println("Cant get last record");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt("id");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		id++;
		return id;
	}

}
