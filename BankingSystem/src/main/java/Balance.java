import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Balance
 */
@WebServlet("/Balance")
public class Balance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Balance() {
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
			double amount=0.0;
			if(rs.next()) {
				amount=rs.getDouble(4);
				out.print("Welcome "+name+"<br>");
				out.print("your account number is "+acc_num+"<br>");
				out.print("Your curent balance in your account is "+amount);
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
