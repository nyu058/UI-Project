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
@WebServlet("/Orders")
public class Orders extends HttpServlet {
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		String oid= request.getParameter("oid");
		Connect db = new Connect();
		db.openConnection();

		updateStatus(oid, status, db);
		response.sendRedirect("orderDetail.jsp?orderid="+oid);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		String oid= request.getParameter("oid");
		Connect db = new Connect();
		db.openConnection();

		updateStatus(oid, status, db);
		response.sendRedirect("orderDetail.jsp?orderid="+oid);
		
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
							+ "</h4></td><td><a href=\"Rate.jsp\"><button type=\"submit\" class=\"btn btn-sm btn-primary green\"style=\"border-radius: 8px\"> RATE</button></a></td></tr>";

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
			rs = st.executeQuery("Select round(cast(total as numeric),2), status, to_char(order_date, 'Mon DD, YYYY'), table_num, orders.id from orders inner join restaurant on orders.res_id=restaurant.id where res_id=" + id);
		} catch (Exception e) {
			System.out.println("Cant get order info");
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					orderList += "<tr> <td><h4>" + rs.getString("table_num")
							+ "</h4></td><td><h4>" + rs.getString(3)+ "</h4></td><td><h4>$" + rs.getString(1)+"</h4></td>" + status(rs.getBoolean(2))
							+ "</h4><td style=\"text-align:right\"><a href=\"orderDetail.jsp?orderid=" +rs.getString(5)+"\" class=\"btn btn-primary green\"><b>Details/Edit</b></a></td></td></tr>";

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

	/**
	 * @param restaurant_id
	 * @return restaurant_name
	 */
	public String getRestaurantName(String restaurant_id, Connect conn) {
		String restaurant_name = null;
		Statement st;
		Connection connection = conn.getConnection();
		try {
			st = connection.createStatement();
			rs = st.executeQuery("select name from restaurant  where restaurant.id=" + restaurant_id);
		} catch (Exception e) {
			System.out.println("Cant get restaurant name");
		}
		try {
			if (rs != null) {
				while (rs.next()) {
					restaurant_name = rs.getString("name");
				}
			}

		} catch (Exception e) {
			System.out.println("Error creating table " + e);
		}
		
		return restaurant_name;
	}
	
public String getOrderDetail(String id, Connect db) {
	String result=null;
	Statement st;

	Connection connection = db.getConnection();
	try {
		st = connection.createStatement();
		rs = st.executeQuery("Select round(cast(total as numeric),2), status, to_char(order_date, 'Mon DD, YYYY'), table_num from orders where id=" + id);
	} catch (Exception e) {
		System.out.println("Cant get order info");
		e.printStackTrace();
	}
	try {
		if (rs != null) {
			while (rs.next()) {
				result += "<tr> <td><b>Table Number:&nbsp;</b></td><td><h4>" + rs.getString("table_num")
						+ "</h4></td><td><b>Date: &nbsp;</b></td><td><h4>" + rs.getString(3)+ "</h4></td></tr><tr><td><b>Total: &nbsp;</b></td><td><h4>$" + rs.getString(1)+"</h4></td><td><b>Status: &nbsp;</b></td>" + status(rs.getBoolean(2))
						+ "</h4></td></tr>";

			}
		}

	} catch (Exception e) {
		System.out.println("Error creating table " + e);
	}

	return result;
}
public String getOrderItems(String id, Connect db) {
	String result=null;
	Statement st;

	Connection connection = db.getConnection();
	try {
		st = connection.createStatement();
		rs = st.executeQuery("select name, count(dish_id) from cart inner join dish on dish_id=dish.id where order_id="+id+" group by order_id, name");
	} catch (Exception e) {
		System.out.println("Cant get order info");
		e.printStackTrace();
	}
	try {
		if (rs != null) {
			while (rs.next()) {
				result += "<tr><td><h4>" + rs.getString(1)+ "</h4></td><td></td><td></td><td><h4>x" + rs.getString(2)+"</h4></td></tr>";

			}
		}

	} catch (Exception e) {
		System.out.println("Error creating table " + e);
	}

	return result;
}
	
	public void updateStatus(String id, String status, Connect db) {
		Statement st;
		Connection connection = db.getConnection();
		Boolean statusVal = false;
		if(status.equals("1")) {
			statusVal = true;
		}
		try {
			st = connection.createStatement();
			st.executeUpdate("update orders set status="+statusVal+" where id="+id);
		} catch (Exception e) {
			System.out.println("Cant change status info");
			e.printStackTrace();
		}
	}
	public String status(Boolean val) {
		if(!val) {
			return "<td style=\"color:red\"><h4>Incomplete";
		}else {
			return "<td style=\"color:green\"><h4>Completed";
		}
	}
}
