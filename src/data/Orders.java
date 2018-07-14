package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.Connect;

/**
 * Servlet implementation class OrderCustomer
 */
@WebServlet("/OrderCustomer")
public class Orders extends HttpServlet {
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	public String getCustomerOrder(String id, Connect conn) {
		String orderList=null;
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select round(cast(total as numeric),2), status, to_char(order_date, 'Mon DD, YYYY'), name from orders inner join restaurant on orders.res_id=restaurant.id where cus_id=" + id);
		} catch (Exception e) {
			System.out.println("Cant get order info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					orderList += "<tr> <td><h4>" + rs.getString("name")
							+ "</h4></td><td><h4>" + rs.getString(3)+ "</h4></td><td><h4>$" + rs.getString(1)+"</h4></td>" + status(rs.getBoolean(2))
							+ "</h4></td></tr>";

				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		
		
		return orderList;
	}
	
	public String getRestaurantOrder(String id, Connect conn) {
		String orderList=null;
		Statement st;

		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("Select round(cast(total as numeric),2), status, to_char(order_date, 'Mon DD, YYYY'), table_num from orders inner join restaurant on orders.res_id=restaurant.id where res_id=" + id);
		} catch (Exception e) {
			System.out.println("Cant get order info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					orderList += "<tr> <td><h4>" + rs.getString("table_num")
							+ "</h4></td><td><h4>" + rs.getString(3)+ "</h4></td><td><h4>$" + rs.getString(1)+"</h4></td>" + status(rs.getBoolean(2))
							+ "</h4></td></tr>";

				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}

		
		
		return orderList;
	}
	public String getRestaurantID(String oid, Connect conn) {
		String rid = null;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select id from restaurant  where owner_id="+oid);
		} catch (Exception e) {
			System.out.println("Cant get id");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					rid = rs.getString("id");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		
		return rid;
	}

	
	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}
	public String status(Boolean val) {
		if(val) {
			return "<td style=\"color:red\"><h4>Active";
		}else {
			return "<td style=\"color:green\"><h4>Completed";
		}
	}
}
