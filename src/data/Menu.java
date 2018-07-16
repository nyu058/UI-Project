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
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private int index = 0;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		request.setAttribute("id", id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String getAppetizers(String id, Connect conn) {
		String result = null;
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select id, name, description, round(cast(price as numeric),2) from dish where res_id =" + id
							+ "and category='app'");
		} catch (Exception e) {
			System.out.println("Cant search info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					result += "<tr><td class=\"col-xs-3\"><h4>" + rs.getString("name")
							+ "</h4></td><td class=\"col-xs-5\"><h5>" + rs.getString("description")
							+ "</h5></td><td class=\"col-xs-1\"><p>4/5</p></td><td class=\"col-xs-1\">$<p style=\"display: inline\" id=\"dp"+index+"\">"
							+ rs.getString(4)
							+ "</h4></td><td class=\"col-xs-2\"><button id=\"down\" class=\"btn btn-default\" onclick=\" down('0','"
							+ index + "')\"style=\"display: inline\">-</button> <input type=\"text\" id=\"myNumber"
							+ index
							+ "\"class=\"form-control\" value=\"0\" style=\"display: inline; width:33px\" readonly/> <button id=\"up\" class=\"btn btn-default\" onclick=\"up('9','"
							+ index + "')\" style=\"display: inline\">+</button></td></tr>";
					index++;
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}
	public String getDesserts(String id, Connect conn) {
		String result = null;
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select id, name, description, round(cast(price as numeric),2) from dish where res_id =" + id
							+ "and category='dessert'");
		} catch (Exception e) {
			System.out.println("Cant search info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					result += "<tr><td class=\"col-xs-3\"><h4 >" + rs.getString("name")
							+ "</h4></td><td class=\"col-xs-5\"><h5>" + rs.getString("description")
							+ "</h5></td><td class=\"col-xs-1\"><p>4/5</p></td><td class=\"col-xs-1\">$<p style=\"display: inline\" id=\"dp"+index+"\">"
							+ rs.getString(4)
							+ "</h4></td><td class=\"col-xs-2\"><button id=\"down\" class=\"btn btn-default\" onclick=\" down('0','"
							+ index + "')\"style=\"display: inline\">-</button> <input type=\"text\" id=\"myNumber"
							+ index
							+ "\"class=\"form-control\" value=\"0\" style=\"display: inline; width:33px\" readonly/> <button id=\"up\" class=\"btn btn-default\" onclick=\"up('9','"
							+ index + "')\" style=\"display: inline\">+</button></td></tr>";
					index++;
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}
	public String getMains(String id, Connect conn) {
		String result = null;
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(
					"select id, name, description, round(cast(price as numeric),2) from dish where res_id =" + id
							+ "and category='main'");
		} catch (Exception e) {
			System.out.println("Cant search info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					result += "<tr><td class=\"col-xs-3\"><h4>" + rs.getString("name")
							+ "</h4></td><td class=\"col-xs-5\"><h5>" + rs.getString("description")
							+ "</h5></td><td class=\"col-xs-1\"><p>4/5</p></td><td class=\"col-xs-1\">$<p style=\"display: inline\" id=\"dp"+index+"\">"
							+ rs.getString(4)
							+ "</h4></td><td class=\"col-xs-2\"><button id=\"down\" class=\"btn btn-default\" onclick=\" down('0','"
							+ index + "')\"style=\"display: inline\">-</button> <input type=\"text\" id=\"myNumber"
							+ index
							+ "\"class=\"form-control\" value=\"0\" style=\"display: inline; width:33px\" readonly/> <button id=\"up\" class=\"btn btn-default\" onclick=\"up('9','"
							+ index + "')\" style=\"display: inline\">+</button></td></tr>";
					index++;
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		return result;
	}


}
