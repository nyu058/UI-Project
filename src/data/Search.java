package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private  ResultSet rs;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		request.setAttribute("search", search);

		RequestDispatcher dispatcher = request.getRequestDispatcher("searchResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
public String getSearchResult(String text, Connect conn) {
	String result= null;
	Statement st;
	
	Connection connection = conn.getConnection();
	try {
		st = connection.createStatement();
		rs = st.executeQuery("Select id, name from restaurant where name like'%" + text + "%'");
	} catch (Exception e) {
		System.out.println("Cant search info");
		e.printStackTrace();
	}
	try {
		if (rs != null) {
			while (rs.next()) {
				result +="<tr> <td><h4>"+ rs.getString("name")+"</h4></td><td style=\"text-align:right\"><form method=\"get\" action=\"Menu\"><input type=\"hidden\"name=\"id\" value=\"" + 
									 rs.getString("id") + "\"><input type=\"submit\" class=\"btn btn-primary green\" value=\"Order Now!\"></form></td></tr>";

			}
		}
		
	} catch (Exception e) {
		System.out.println("Error creating table " + e);
	}
	
	return result;
}

}
