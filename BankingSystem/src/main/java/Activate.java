import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Activate
 */
@WebServlet("/Activate")
public class Activate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int acc_num=Integer.parseInt(request.getParameter("acc_num"));
		String name=request.getParameter("acc_name");
		String password=request.getParameter("psw");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/7amdb","root","root");
			PreparedStatement ps=con.prepareStatement("select * from accountInfo where acc_num=? and name=? and password=?");
			ps.setInt(1, acc_num);
			ps.setString(2, name);
			ps.setString(3, password);
			ResultSet rs=ps.executeQuery();
			String status;
			if(rs.next()) {
				status=rs.getString(7);
				PreparedStatement ps2=con.prepareStatement("update accountInfo set status='activate' where acc_num=?");
				ps2.setInt(1, acc_num);
			    int i=ps2.executeUpdate();
				if(i>0) {
					out.print("<center>"+"Successfully activated your account......... "+"</center>");
				}
				else {
					out.print("Again check your account details.Please try again!");
				}
			}
			else {
				out.print("Enter correct details....");
			}
		}
		catch(Exception ex) {
			out.print(ex);
		}
	}

}
